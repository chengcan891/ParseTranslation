package com.chengcan;

import com.chengcan.entity.FileInfo;
import com.chengcan.html.HtmlExport;
import com.chengcan.parse.Parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        InputStream is = Main.class.getResourceAsStream("result.html");

        File initFile = new File("D:\\res\\values");
        File aimFile = new File("D:\\res\\values-en");

        //解析两个文件夹内翻译文件
        List<FileInfo> fileInfos = Parser.parse(initFile, aimFile);

        //导出文件结果到html
//        HtmlExport.export(new File("result.html"), new File("exprotResult.html"), fileInfos);
        HtmlExport.export(is, new File("exportResult.html"), fileInfos);

    }


}
