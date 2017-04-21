package com.adj.workreporter;

import java.net.URL;

/**
 * 应用常量
 * Created by dhx on 2017/4/10.
 */
public class Constants {
    private static String DIRECTORY = "";
    static {
        URL location = Constants.class.getProtectionDomain().getCodeSource().getLocation();
        DIRECTORY = location.getFile();
    }
    static final String DATA_DIR_URL = "data/";
    public static final String DAILY_WORKS_DATA_URL = DIRECTORY + DATA_DIR_URL + "today.txt";
    public static final String LOG_URL = DIRECTORY + DATA_DIR_URL + "logback";
//    public static final String OUTPUT_DIR = "target/out";
    public static final String WEEKLY_REPORT_OUTPUT__FILE_PATH = "F://weekReport.txt";


    public final static String DATABASE_URL = "jdbc:sqlite:" + DIRECTORY + DATA_DIR_URL + "works.db";
}
