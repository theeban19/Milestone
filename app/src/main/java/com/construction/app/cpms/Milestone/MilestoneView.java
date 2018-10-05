package com.construction.app.cpms.Milestone;

import android.support.v4.graphics.PathSegment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class MilestoneView {

    private String id;
    private String name;
    private String desc;
    private String task;
    private String empId;
    private String date;

    public MilestoneView(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public MilestoneView(String id, String name, String desc, String task, String empId, String date) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.task = task;
        this.empId = empId;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTask() {
        return this.task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getEmpId() {
        return this.empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDaysLeft() {
        Date currentDate = new Date();

        //// This is a String. Convert this to Date object.
        String startDateString = this.getDate();

        System.out.println(currentDate.toString()+" | "+startDateString);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate;
        long days = 0;
        try {
            endDate = sdf.parse(startDateString);
            long timeDiff = endDate.getTime() - currentDate.getTime();
            days = (timeDiff / (60*60*24*1000));
        } catch (ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
        // Get the difference between this.getDate() date object and currentDate object.
        // If it is 0 or greater, return the difference
        // Return "ENDED"

        // This object can interpret strings representing dates in the format MM/dd/yyyy
        if (days >= 0) {
            return String.valueOf(days);
        } else {
            return "ENDED";
        }
    }

/*    public void add(Object object) {
    }

    public void getCount() {
    }

    public Object getItem(int position) {
    }*/
}
