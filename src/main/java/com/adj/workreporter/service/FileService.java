package com.adj.workreporter.service;

import com.adj.workreporter.Constants;
import com.adj.workreporter.model.DayWorks;
import com.google.gson.Gson;

import java.io.*;

/**
 * 数据持久化service
 * Created by dhx on 2017/4/10.
 */
public class FileService {
    private Gson gson = new Gson();

    public void saveDayWorks(DayWorks dayWorks) throws IOException {
        File directory = new File(Constants.DATA_DIR);
        if (!directory.exists())
            if (!directory.mkdirs())
                throw new RuntimeException("wtf");
        File dayWorksFile = new File(Constants.DATA_DIR, dayWorks.getDateString());
        if (!dayWorksFile.exists())
            if (!dayWorksFile.createNewFile())
                throw new RuntimeException("wtf");
        String dayWorksString = gson.toJson(dayWorks);
        try (FileWriter fileWriter = new FileWriter(dayWorksFile, false);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(dayWorksString);
            bufferedWriter.flush();
        }
    }
}
