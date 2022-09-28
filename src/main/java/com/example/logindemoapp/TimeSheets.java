package com.example.logindemoapp;

public class TimeSheets {
    private String companyName;
    private String jobType;
    private int workedHours;

    public TimeSheets(String companyName, String jobType, int workedHours) {
        this.companyName = companyName;
        this.jobType = jobType;
        this.workedHours = workedHours;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }
}
