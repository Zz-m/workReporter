package com.adj.workreporter.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 一项工作
 * Created by dhx on 2017/4/10.
 */
@DatabaseTable(tableName = "works")
public class Work {

    public static final String EPOCH_SECOND_FIELD_NAME = "epoch_second";
    public static final String MESSAGE_FIELD_NAME = "message";

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = EPOCH_SECOND_FIELD_NAME, canBeNull = false, index = true)
    private long epochSecond;
    @DatabaseField(columnName = MESSAGE_FIELD_NAME, canBeNull = false)
    private String message;

    // all persisted classes must define a no-arg constructor with at least package visibility
    Work(){}

    public Work(long epochSecond, String message) {
        this.epochSecond = epochSecond;
        this.message = message;
    }

    public long getEpochSecond() {
        return epochSecond;
    }

    public String getMessage() {
        return message;
    }
}
