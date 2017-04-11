package com.adj.workreporter.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 每天的工作
 * Created by dhx on 2017/4/10.
 */
public class DayWorks {
    private LocalDate date;
    private List<Work> works;

    public DayWorks() {
        date = LocalDate.now();
        works = new ArrayList<>();
    }

    public DayWorks(LocalDate date, List<Work> works) {
        this.date = date;
        this.works = works;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public String getDateString() {
        return date.format(DateTimeFormatter.ISO_DATE);
    }
}
