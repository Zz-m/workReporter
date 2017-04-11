package com.adj.workreporter.service;

import com.adj.workreporter.Constants;
import com.adj.workreporter.model.DayWorks;
import com.adj.workreporter.model.Work;
import com.adj.workreporter.util.SqlUtil;
import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.io.*;
import java.sql.SQLException;

/**
 * 数据持久化service
 * Created by dhx on 2017/4/10.
 */
public class WorkService {
    private Gson gson = new Gson();
    private Dao<Work, Integer> workDao;

    public WorkService() {
        workDao = SqlUtil.getWorkDao();
    }

    public void saveWork(Work work) throws SQLException {
        workDao.create(work);
    }
}
