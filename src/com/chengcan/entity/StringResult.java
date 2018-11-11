package com.chengcan.entity;

public class StringResult {
    private StringResource original;
    private StringResource aim;
    private int status;

    public StringResult(StringResource original) {
        this(original, null, StringResourceConstant.STATUS_NOT_FIND);
    }

    public StringResult(StringResource original, StringResource aim) {
        this(original, aim, StringResourceConstant.STATUS_OK);
    }

    public StringResult(StringResource original, StringResource aim, int status) {
        this.original = original;
        this.aim = aim;
        this.status = status;
    }

    public StringResource getOriginal() {
        return original;
    }

    public void setOriginal(StringResource original) {
        this.original = original;
    }

    public StringResource getAim() {
        return aim;
    }

    public void setAim(StringResource aim) {
        this.aim = aim;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StringResult{" +
                "original=" + original +
                ", aim=" + aim +
                ", status=" + status +
                '}';
    }
}
