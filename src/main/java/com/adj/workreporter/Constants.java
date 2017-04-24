package com.adj.workreporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

/**
 * 应用常量
 * Created by dhx on 2017/4/10.
 */
public class Constants {
    private static final Logger logger = LoggerFactory.getLogger(Constants.class);
    private static String DIRECTORY = "";

    static {
//        URL location = Constants.class.getProtectionDomain().getCodeSource().getLocation();
//        DIRECTORY = location.getFile();
        try {
            DIRECTORY = Constants.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            DIRECTORY = DIRECTORY.substring(0, DIRECTORY.lastIndexOf("/") + 1);
            logger.debug("文件基础路径： {}", DIRECTORY);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    static final String DATA_DIR_URL = DIRECTORY + "data/";
    public static final String DAILY_WORKS_DATA_URL = DATA_DIR_URL + "today.txt";
    public static final String LOG_URL = DATA_DIR_URL + "logback";
    //    public static final String OUTPUT_DIR = "target/out";
    public static final String WEEKLY_REPORT_OUTPUT_FILE_PATH = "F://weekReport.txt";


    public final static String DATABASE_URL = "jdbc:sqlite:" + DATA_DIR_URL + "works.db";
}
