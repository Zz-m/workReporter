package com.adj.workreporter;


import com.adj.workreporter.model.Work;
import com.adj.workreporter.service.WorkService;
import com.adj.workreporter.util.LogbackUtil;
import com.adj.workreporter.util.SqlUtil;
import com.adj.workreporter.worker.DailyGatherWorker;
import com.adj.workreporter.worker.WeeklyExcelReporter;
import com.adj.workreporter.worker.WeeklySimpleReporter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

import static com.adj.workreporter.Constants.DATA_DIR_URL;

/**
 * 工作报告
 * Created by dhx on 2017/4/10.
 */
public class WorkReporter {
    private static final Logger logger = LoggerFactory.getLogger(WorkReporter.class);

    public static void main(String[] args) {
        try {
            init();
            start(args);
            shutDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() throws Exception {
        File file = new File(DATA_DIR_URL);
        if (!file.exists()) {
            boolean success = file.mkdirs();
            if (!success)
                throw new RuntimeException("mkdir fail");
        }
        SqlUtil.init();
        LogbackUtil.init();
    }

    private static void start(String[] args) throws Exception {
        if (args.length > 0) {
            if (args[0].equals("-d")) {
                logger.debug("-d");
                new DailyGatherWorker().gather();
            } else if (args[0].equals("-week")) {
                logger.debug("-week");
                new WeeklySimpleReporter().generateReport();
            } else {
                logger.error("未知参数{}", args[0]);
            }
        } else {
            logger.error("没有参数");
        }
    }

    private static void shutDown() throws Exception {
        SqlUtil.close();
    }


    private static void readString5() throws Exception {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if ("0".equals(s))
                break;
            if ("1".equals(s))
                logger.debug("1");
            else
                logger.debug(s);
        }
    }

    private static void testTime() {
        Instant instant = Instant.now();
        LocalDateTime dateTime = LocalDateTime.now();
        logger.debug("instant: " + instant);
        logger.debug("dateTime: " + dateTime);
    }

//    private static void testRead() throws IOException, ClassNotFoundException {
//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("asd"));
//        Map<String, String> data = (Map<String, String>) objectInputStream.readObject();
//        logger.debug(data.get("a123"));
//        logger.debug(data.get("asd"));
//    }

    private static void testSave() throws SQLException {
        WorkService workService = new WorkService();
        Work work = new Work(Instant.now().getEpochSecond(), "测试保存的work");
        workService.saveWork(work);
    }


}
