package com.adj.workreporter.worker;

import com.adj.workreporter.Constants;
import com.adj.workreporter.model.Work;
import com.adj.workreporter.service.WorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLException;
import java.time.Instant;

/**
 * 每日工作内容入库
 * Created by dhx on 2017/4/11.
 */
public class DailyGatherWorker {
    private Logger logger = LoggerFactory.getLogger(DailyGatherWorker.class);

    private WorkService workService = new WorkService();

    public void gather() throws SQLException, IOException {
        File dailyFile = new File(Constants.DAILY_WORKS_DATA_URL);
        if (!dailyFile.exists()) {
            logger.debug("文件不存在 url： " + dailyFile.toURI());
            dailyFile.createNewFile();
            return;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dailyFile), "UTF8"))) {
            String line = br.readLine();
            while (line != null) {
                line = line.trim();
                if (line.length() != 0) {
                    logger.debug("工作内容： " + line);
                    Work work = new Work(Instant.now().getEpochSecond(), line);
                    workService.saveWork(work);
                }
                line = br.readLine();
            }
        }

        try (PrintWriter writer = new PrintWriter(dailyFile)) {
            writer.print("");
        }
    }
}
