package com.chengcan.parse;

import com.chengcan.entity.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Parser {


    public static List<FileInfo> parse(File originalDir, File aimDir) {
        if (!originalDir.exists() || !aimDir.exists() || originalDir.isFile() || aimDir.isFile()) {
            return null;
        }

        List<FileInfo> fileInfos = new ArrayList<>();

        List<File> originalFiles = new ArrayList<>(Arrays.asList(originalDir.listFiles()));
        List<File> aimFiles = new ArrayList<>(Arrays.asList(aimDir.listFiles()));

        for (int i = 0; i < originalFiles.size(); i++) {
            File originalFile = originalFiles.get(i);
            boolean isFind = false;
            File aimFile = null;
            for (int j = 0; j < aimFiles.size(); j++) {
                aimFile = aimFiles.get(j);
                if (originalFile.getName().equals(aimFile.getName())) {
                    aimFiles.remove(j);
                    isFind = true;
                    break;
                }
            }
            FileInfo fileInfo = null;
            if (!isFind) {
                fileInfo = new FileInfo(originalFile);
            } else {
                fileInfo = new FileInfo(originalFile, aimFile);
            }
            fileInfos.add(fileInfo);
        }

        Iterator<FileInfo> iterator = fileInfos.iterator();
        while (iterator.hasNext()) {
            final FileInfo fileInfo = iterator.next();
            if (fileInfo.getStatus() != FileConstant.STATUS_OK) {
                continue;
            }


            parseFile(fileInfo.getOriginalFile(), fileInfo.getAimFile(), new ParseResult() {
                @Override
                public void onMatched(StringResource original, StringResource aim) {
                    fileInfo.getStringResults().add(new StringResult(original, aim));
                }

                @Override
                public void onNotMatched(StringResource original, StringResource aim) {
                    fileInfo.getStringResults().add(new StringResult(original, aim, StringResourceConstant.STATUS_NOT_MATCHED));
                }

                @Override
                public void onNotFind(StringResource original) {
                    fileInfo.getStringResults().add(new StringResult(original));
                }
            });
        }

        return fileInfos;
    }


    public static void parseFile(File originalFile, File aimFile, ParseResult result) {

        if (!originalFile.exists() || !aimFile.exists()) {
            return;
        }


        SAXParserFactory sParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = null;

        List<StringResource> originalFileList = null;
        List<StringResource> aimFileList = null;
        try {
            parser = sParserFactory.newSAXParser();
            SAXParseHandler handler = new SAXParseHandler();
            parser.parse(originalFile, handler);
            originalFileList = handler.getResourceList();
            parser.parse(aimFile, handler);
            aimFileList = handler.getResourceList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < originalFileList.size(); i++) {
            boolean isFind = false;
            boolean isNotMatch = false;
            StringResource stringResource = originalFileList.get(i);
            StringResource aim = null;
            for (int j = 0; j < aimFileList.size(); j++) {
                if (stringResource.getName().equals(aimFileList.get(j).getName())) {
                    aim = aimFileList.get(j);
                    if (stringResource.equals(aimFileList.get(j))) {
                        aimFileList.remove(j);
                        isFind = true;

                    } else {
                        isNotMatch = true;
                    }

                    break;
                }

            }

            if (isFind) {
                result.onMatched(stringResource, aim);
            } else {
                if (isNotMatch) {
                    result.onNotMatched(stringResource, aim);
                } else {
                    result.onNotFind(stringResource);
                }
            }


        }
    }
}

