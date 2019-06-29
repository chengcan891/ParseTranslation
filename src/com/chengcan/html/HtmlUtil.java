package com.chengcan.html;

import com.chengcan.entity.FileConstant;
import com.chengcan.entity.FileInfo;
import com.chengcan.entity.StringResourceConstant;
import com.chengcan.entity.StringResult;

import java.util.List;

public class HtmlUtil {


    public static String getStringInfo(List<FileInfo> fileInfoList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileInfoList.size(); i++) {
            FileInfo fileInfo = fileInfoList.get(i);
            if (fileInfoList.get(i).getStatus() != FileConstant.STATUS_OK) {
                continue;
            }
            String fileTilte = String.format("<h4>%1$s</h4>", fileInfo.getOriginalFile().getName());
            String tableStart = "<table border=\"1\">";
            String tableEnd = "</table>";
            String tableHead = " <tr>\n" +
                    "        <th>序号</th>\n" +
                    "        <th>资源名</th>\n" +
                    "        <th>匹配状态</th>\n" +
                    "        <th>原始资源格式化值</th>\n" +
                    "        <th>目标资源值格式化值</th>\n" +
                    "    </tr>\n";
            String fileInfoText = " <tr>\n" +
                    "        <td>%1$d</td>\n" +
                    "        <td>%2$s</td>\n" +
                    "        <td>%3$s</td>\n" +
                    "        <td>%4$s</td>\n" +
                    "        <td>%5$s</td>\n" +
                    "    </tr>\n";
            sb.append(fileTilte);
            sb.append(tableStart);
            sb.append(tableHead);

            for (int j = 0; j < fileInfo.getStringResults().size(); j++) {
                List<StringResult> stringResults = fileInfo.getStringResults();

                switch (stringResults.get(j).getStatus()) {
                    case StringResourceConstant.STATUS_OK:
                        break;
                    case StringResourceConstant.STATUS_NOT_MATCHED:

                        if (stringResults.get(j).getOriginal().isOtherString() || stringResults.get(j).getAim().isOtherString()) {

                            sb.append(String.format(fileInfoText, j + 1,
                                    stringResults.get(j).getOriginal().getName(),
                                    "资源不匹配",
                                    stringResults.get(j).getOriginal().getValue(),
                                    stringResults.get(j).getAim().getValue()));
                        } else {
                            sb.append(String.format(fileInfoText, j + 1,
                                    stringResults.get(j).getOriginal().getName(),
                                    "资源不匹配",
                                    stringResults.get(j).getOriginal().getRegs(),
                                    stringResults.get(j).getAim().getRegs()));
                        }


                        break;
                    case StringResourceConstant.STATUS_NOT_FIND:
                        sb.append(String.format(fileInfoText, j + 1,
                                stringResults.get(j).getOriginal().getName(),
                                "资源没找到",
                                "",
                                ""));
                        break;
                }
            }
            sb.append(tableEnd);

        }
        return sb.toString();
    }


    public static String getFileInfo(List<FileInfo> fileInfoList) {

        StringBuilder sb = new StringBuilder();
        String tableStart = "<table border=\"1\">";
        String tableEnd = "</table>";
        String tableHead = " <tr>\n" +
                "        <th>序号</th>\n" +
                "        <th>文件名</th>\n" +
                "        <th>状态</th>\n" +
                "    </tr>\n";
        String fileInfo = " <tr>\n" +
                "        <td>%1$d</td>\n" +
                "        <td>%2$s</td>\n" +
                "        <td>%3$s</td>\n" +
                "    </tr>\n";


        sb.append(tableStart);
        sb.append(tableHead);

        for (int i = 0; i < fileInfoList.size(); i++) {
            sb.append(String.format(fileInfo, i + 1, fileInfoList.get(i).getOriginalFile().getName(), getStatusString(fileInfoList.get(i).getStatus())));
        }
        sb.append(tableEnd);

        return sb.toString();
    }

    private static String getStatusString(int status) {
        return status == 0 ? "文件匹配成功" : "文件未找到";
    }
}
