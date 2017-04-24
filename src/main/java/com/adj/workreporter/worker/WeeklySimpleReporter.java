package com.adj.workreporter.worker;

import com.adj.workreporter.Constants;
import com.adj.workreporter.model.Work;
import com.adj.workreporter.service.WorkService;
import com.adj.workreporter.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

/**
 * 生成每周的简单报告
 * Created by dhx on 2017/4/19.
 */
public class WeeklySimpleReporter {
    private Logger logger = LoggerFactory.getLogger(WeeklySimpleReporter.class);
    private WorkService workService = new WorkService();
    public void generateReport() {
        logger.debug("generateReport");
        long now = Instant.now().getEpochSecond();
        try {
            new DailyGatherWorker().gather();
            List<Work> workList = workService.queryWorksInPeriod(DateTimeUtil.getWeekStartEpochSecond(now), DateTimeUtil.getWeekEndEpochSecond(now));
            File weekReport = new File(Constants.WEEKLY_REPORT_OUTPUT_FILE_PATH);
            if (!weekReport.exists()) {
                logger.debug("文件不存在，准备创建");
                boolean success = weekReport.createNewFile();
                if (!success) {
                    logger.error("创建周报告文件失败");
                    return;
                }
            } else {
                logger.debug("文件已经存在");
            }
            try (PrintWriter writer = new PrintWriter(weekReport)) {
                writer.print("");
                int i = 1;
                for (Work work : workList) {
                    writer.println("" + i++ + "、" + work.getMessage());
                }
                logger.info("完成周报告");
            }
        } catch (SQLException e) {
            logger.error("查询周工作异常", e);
        } catch (FileNotFoundException e) {
            logger.error("不可能哦", e);
        } catch (IOException e) {
            logger.error("创建文件异常", e);
        }
    }
}
