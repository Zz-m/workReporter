package com.adj.workreporter.worker;

import com.adj.workreporter.Constants;
import com.adj.workreporter.model.Work;
import com.adj.workreporter.service.WorkService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Scanner;

/**
 * 每日工作内容入库
 * Created by dhx on 2017/4/11.
 */
public class DailyGatherWorker {

    private WorkService workService = new WorkService();

    public void gather() throws FileNotFoundException, SQLException {
        File dailyFile = new File(Constants.DAILY_WORKS_DATA_URL);
        if (!dailyFile.exists()) {
            System.out.println("文件不存在 url： " + dailyFile.toURI());
            return;
        }

        try (Scanner scanner = new Scanner(new FileInputStream(dailyFile))) {
            while (scanner.hasNext()) {
                String msg = scanner.nextLine();
                System.out.println("工作内容： " + msg);
                if (msg != null && msg.trim().length() > 0) {
                    Work work = new Work(Instant.now().getEpochSecond(), msg.trim());
                    workService.saveWork(work);
                }
            }

        }

        try (PrintWriter writer = new PrintWriter(dailyFile)) {
            writer.print("");
        }
    }
}
