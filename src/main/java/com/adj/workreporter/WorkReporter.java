package com.adj.workreporter;


import com.adj.workreporter.model.Work;
import com.adj.workreporter.service.WorkService;
import com.adj.workreporter.util.SqlUtil;
import com.adj.workreporter.worker.DailyGatherWorker;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

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
    }

    private static void start(String[] args) throws Exception {
        if (args.length > 0) {
            if (args[0].equals("g")) {
                new DailyGatherWorker().gather();
            }
        } else {
            System.out.println("呵呵 over");
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
                System.out.println("1");
            else
                System.out.println(s);
        }
    }

    private static void testTime() {
        Instant instant = Instant.now();
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("instant: " + instant);
        System.out.println("dateTime: " + dateTime);
    }

    private static void testRead() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("asd"));
        Map<String, String> data = (Map<String, String>) objectInputStream.readObject();
        System.out.println(data.get("a123"));
        System.out.println(data.get("asd"));
    }

    private static void testSave() throws SQLException {
        WorkService workService = new WorkService();
        Work work = new Work(Instant.now().getEpochSecond(), "测试保存的work");
        workService.saveWork(work);
    }


    private static void testCreateCell() throws IOException {
//        Workbook wb = new HSSFWorkbook();
        Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short) 0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("target/workbook.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
