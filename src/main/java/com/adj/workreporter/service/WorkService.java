package com.adj.workreporter.service;

import com.adj.workreporter.model.Work;
import com.adj.workreporter.util.SqlUtil;
import com.j256.ormlite.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * 数据持久化service
 * Created by dhx on 2017/4/10.
 */
public class WorkService {
    private Logger logger = LoggerFactory.getLogger(WorkService.class);
    private Dao<Work, Integer> workDao;

    public WorkService() {
        workDao = SqlUtil.getWorkDao();
    }

    public void saveWork(Work work) throws SQLException {
        workDao.create(work);
    }

    public List<Work> queryWorksInPeriod(long startEpochSecond, long endEpochSecond) throws SQLException {
        logger.debug("收到查询 [queryWorksInPeriod] beginSecond:{}  + endSecond:{}", startEpochSecond, endEpochSecond);
        return workDao.queryBuilder().where().ge(Work.EPOCH_SECOND_FIELD_NAME, startEpochSecond).and()
                .le(Work.EPOCH_SECOND_FIELD_NAME, endEpochSecond).query();
    }
}
