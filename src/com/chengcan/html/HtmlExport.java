package com.chengcan.html;

import com.chengcan.entity.FileInfo;

import java.io.*;
import java.util.List;
import java.util.Locale;

public class HtmlExport {
    public static void export(File file, File aimFile, List<FileInfo> fileInfoList) {
        try {
            export(new FileInputStream(file), aimFile, fileInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void export(InputStream fis, File aimFile, List<FileInfo> fileInfoList) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                sb.append(temp);
            }
            fis.close();
            String result = String.format(Locale.ROOT, sb.toString(), HtmlUtil.getFileInfo(fileInfoList), HtmlUtil.getStringInfo(fileInfoList));

            FileOutputStream fos = new FileOutputStream(aimFile);
            fos.write(result.getBytes("utf-8"));
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
