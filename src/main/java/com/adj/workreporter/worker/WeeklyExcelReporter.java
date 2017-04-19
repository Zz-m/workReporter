package com.adj.workreporter.worker;

import com.adj.workreporter.model.Work;
import com.adj.workreporter.service.WorkService;
import com.adj.workreporter.util.DateTimeUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 每周工作报告excel生成
 * Created by dhx on 2017/4/12.
 */
public class WeeklyExcelReporter {

    private static final String FONT_NAME = "宋体";

    private Logger logger = LoggerFactory.getLogger(WeeklyExcelReporter.class);
    private WorkService workService = new WorkService();

    public void generateReport() {
        logger.debug("generateReport");
        long now = Instant.now().getEpochSecond();
        try {
            List<Work> worksOfThisWeek = workService.queryWorksInPeriod(DateTimeUtil.getWeekStartEpochSecond(now), DateTimeUtil.getWeekEndEpochSecond(now));
            for (Work work : worksOfThisWeek) {
                logger.info(work.toString());
            }
        } catch (SQLException e) {
            logger.error("查询周工作异常", e);
        }
    }

    private Workbook generateExcelReport(List<Work> works) {
        Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        return wb;
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

    public static void main(String[] args) throws Exception {
        new WeeklyExcelReporter().bitTTTT();
    }

    public void bitTTTT() throws Exception {
        List<Work> worksOfThisWeek = new ArrayList<>();
        worksOfThisWeek.add(new Work(111, "content111"));
        worksOfThisWeek.add(new Work(222, "content222"));
        worksOfThisWeek.add(new Work(333, "content333"));
        worksOfThisWeek.add(new Work(444, "content44"));
        worksOfThisWeek.add(new Work(555, "content555"));
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("new sheet");

        setupTitle(workbook, sheet);
        setupInfo(workbook, sheet);
        setupWorksThisWeek(workbook, sheet, worksOfThisWeek);


        int columnNum = 0;
        for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            logger.info("row max： " + row.getLastCellNum());
            columnNum = columnNum > row.getLastCellNum() ? columnNum : row.getLastCellNum();
        }
        logger.info("最大列： " + columnNum);
        for (int i = 0; i < columnNum; i++) {
            sheet.autoSizeColumn((short) i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("target/workTTTTk.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    //合并单元格
    private static void testMergedRegion() throws IOException {
        Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));


        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("target/workbook.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    private void setupTitle(XSSFWorkbook workbook, XSSFSheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        XSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 18);
        titleFont.setFontName(FONT_NAME);
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFont(titleFont);
        titleStyle.setFillForegroundColor(getForeGroundColor());

        //solid 填充  foreground  前景色
        titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        XSSFRow firstRow = sheet.createRow((short) 0);
//        sheet.autoSizeColumn(0);
        XSSFCell titleCell = firstRow.createCell((short) 0);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("项目周报");
    }

    private void setupInfo(XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFFont blackFont = workbook.createFont();
        blackFont.setBold(true);
        blackFont.setFontName(FONT_NAME);
        XSSFFont normalFont = workbook.createFont();
        normalFont.setFontName(FONT_NAME);
        XSSFCellStyle boldCellStyle = workbook.createCellStyle();
        boldCellStyle.setFont(blackFont);
        boldCellStyle.setAlignment(HorizontalAlignment.CENTER);
        boldCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        boldCellStyle.setFillForegroundColor(getForeGroundColor());
        boldCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        XSSFCellStyle normalStyle = workbook.createCellStyle();
        normalStyle.setFont(normalFont);
        normalStyle.setFillForegroundColor(getForeGroundColor());
        normalStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        XSSFRow secondRow = sheet.createRow((short) 1);
        XSSFCell cell0 = secondRow.createCell(0);
        XSSFCell cell1 = secondRow.createCell(1);
        XSSFCell cell2 = secondRow.createCell(2);
        XSSFCell cell3 = secondRow.createCell(3);
        cell0.setCellStyle(boldCellStyle);
        cell1.setCellStyle(normalStyle);
        cell2.setCellStyle(boldCellStyle);
        cell3.setCellStyle(normalStyle);
        cell0.setCellValue("日期");
        LocalDate nowDate = LocalDate.now();
        String thisMondayString = DateTimeUtil.getMondayOfWeek(nowDate).toString();
        String thisFridayString = DateTimeUtil.getFridayOfWeek(nowDate).toString();
        cell1.setCellValue(thisMondayString + " 至 " + thisFridayString);
        cell2.setCellValue("汇报人");
        cell3.setCellValue("邓晗熙");
    }

    private void setupWorksThisWeek(XSSFWorkbook workbook, XSSFSheet sheet, List<Work> worksOfThisWeek) {
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));
        XSSFFont font0 = workbook.createFont();
        XSSFFont font1 = workbook.createFont();
        font0.setFontName(FONT_NAME);
        font1.setFontName(FONT_NAME);
        XSSFCellStyle style0 = workbook.createCellStyle();
        XSSFCellStyle style1 = workbook.createCellStyle();
        style0.setFont(font0);
        style1.setFont(font1);
        style0.setAlignment(HorizontalAlignment.CENTER);
        style0.setVerticalAlignment(VerticalAlignment.CENTER);
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        style1.setWrapText(true);
//        style0.set

        XSSFRow thirdRow = sheet.createRow(2);
        thirdRow.setHeightInPoints((short) 75);
        XSSFCell cell0 = thirdRow.createCell(0);
        XSSFCell cell1 = thirdRow.createCell(1);
        cell0.setCellValue("本周工作内容");
        cell0.setCellStyle(style0);


    }

    private XSSFColor getForeGroundColor() {
        byte[] rgb = new byte[3];
        rgb[0] = (byte) 192; // red
        rgb[1] = (byte) 217; // green
        rgb[2] = (byte) 241; // blue
        return new XSSFColor(rgb); // #f2dcdb
    }

}
