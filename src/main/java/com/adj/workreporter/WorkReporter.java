package com.adj.workreporter;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 工作报告
 * Created by dhx on 2017/4/10.
 */
public class WorkReporter {
    public static void main(String[] args) {
        try {
            readString5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void readString5() throws Exception {
        while(true) {
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

    private static void testSave() throws IOException {
        Map<String, String> data = new HashMap<>();
        data.put("asd", "123");
        FileOutputStream fileOutputStream = new FileOutputStream("asd");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
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
