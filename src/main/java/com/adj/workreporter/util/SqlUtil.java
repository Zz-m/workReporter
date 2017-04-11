package com.adj.workreporter.util;

import com.adj.workreporter.model.Work;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import static com.adj.workreporter.Constants.DATABASE_URL;

/**
 * 数据库工具
 * Created by dhx on 2017/4/11.
 */
public class SqlUtil {
    private static ConnectionSource connectionSource;
    private static Dao<Work, Integer> workDao;

    public static void init() throws Exception {
        connectionSource = new JdbcConnectionSource(DATABASE_URL);
        // setup our database and DAOs
        setupDatabase(connectionSource);

        workDao = DaoManager.createDao(connectionSource, Work.class);

        // read and write some data
        System.out.println("\n\nIt seems to have worked\n\n");
    }

    /**
     * Setup our database
     */
    private static void setupDatabase(ConnectionSource connectionSource) throws Exception {
        // if you need to create the table
        TableUtils.createTableIfNotExists(connectionSource, Work.class);
    }

    public static void close() throws Exception {
        if (connectionSource != null)
            connectionSource.close();
    }

    public static Dao<Work, Integer> getWorkDao() {
        return workDao;
    }
}
