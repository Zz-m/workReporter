package com.adj.workreporter.worker;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 每周工作报告excel生成
 * Created by dhx on 2017/4/12.
 */
public class WeeklyExcelReporter {

    private Logger logger = LoggerFactory.getLogger(WeeklyExcelReporter.class);

    public void generateReport() {
        logger.debug("generateReport");
    }

    private long getWeekStartEpochSecond(long someMoment) {
//        LocalDateTime dateTime = LocalDateTime
        return 1;
    }

    private long getWeekEndEpochSecond(long someMoment) {
        return 1;
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
