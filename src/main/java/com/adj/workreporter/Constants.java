package com.adj.workreporter;

/**
 * 应用常量
 * Created by dhx on 2017/4/10.
 */
public class Constants {
    static final String DATA_DIR_URL = "data/";
    public static final String DAILY_WORKS_DATA_URL = DATA_DIR_URL + "today.txt";
    public static final String LOG_URL = DATA_DIR_URL + "logback";
//    public static final String OUTPUT_DIR = "target/out";
    public static final String WEEKLY_REPORT_OUTPUT__FILE_PATH = "F://weekReport.txt";


    public final static String DATABASE_URL = "jdbc:sqlite:" + DATA_DIR_URL + "works.db";
}
