package com.example.demo.model;

import java.util.Date;

public class LeaveRequest {

    private String category;

    private int noOfDays;

    private Date appliedOn;

    private String description;

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNoOfDays() {
        return this.noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Date getAppliedOn() {
        return this.appliedOn;
    }

    public void setAppliedOn(Date appliedOn) {
        this.appliedOn = appliedOn;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
