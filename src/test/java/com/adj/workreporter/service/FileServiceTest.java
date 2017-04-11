package com.adj.workreporter.service;

import com.adj.workreporter.model.DayWorks;
import com.adj.workreporter.model.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

/**
 * test
 * Created by dhx on 2017/4/10.
 */
public class FileServiceTest {
    private FileService fileService;
    @Before
    public void setUp() throws Exception {
        fileService  = new FileService();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveDayWorks() throws Exception {
        DayWorks dayWorks = new DayWorks();
        Work work1 = new Work(Instant.now().getEpochSecond(), "第一个work");
        Work work2 = new Work(Instant.now().getEpochSecond() + 1000, "第二个work");
        dayWorks.getWorks().add(work1);
        dayWorks.getWorks().add(work2);

    }

}