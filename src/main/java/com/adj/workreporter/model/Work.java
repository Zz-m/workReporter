package com.adj.workreporter.model;

/**
 * 一项工作
 * Created by dhx on 2017/4/10.
 */
public class Work {

    private long epochTime;
    private String message;

    public Work(long epochTime, String message) {
        this.epochTime = epochTime;
        this.message = message;
    }

    public long getEpochTime() {
        return epochTime;
    }

    public String getMessage() {
        return message;
    }
}
