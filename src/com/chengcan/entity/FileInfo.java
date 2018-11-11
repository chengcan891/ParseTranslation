package com.chengcan.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileInfo {
    private File originalFile;
    private File aimFile;
    private List<StringResult> stringResults;
    private int status;

    public FileInfo(File originalFile) {
        this(originalFile, null, FileConstant.STATUS_NOT_FIND);
    }

    public FileInfo(File originalFile, File aimFile) {
        this(originalFile, aimFile, FileConstant.STATUS_OK);
    }


    public FileInfo(File originalFile, File aimFile, int status) {
        this.originalFile = originalFile;
        this.aimFile = aimFile;
        this.status = status;
        this.stringResults = new ArrayList<>();
    }

    public File getOriginalFile() {
        return originalFile;
    }

    public void setOriginalFile(File originalFile) {
        this.originalFile = originalFile;
    }

    public File getAimFile() {
        return aimFile;
    }

    public void setAimFile(File aimFile) {
        this.aimFile = aimFile;
    }

    public List<StringResult> getStringResults() {
        return stringResults;
    }

    public void setStringResults(List<StringResult> stringResults) {
        this.stringResults = stringResults;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "originalFile=" + originalFile +
                ", aimFile=" + aimFile +
                ", stringResults=" + stringResults +
                ", status=" + status +
                '}';
    }
}
