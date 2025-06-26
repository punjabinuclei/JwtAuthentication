package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String category;

    @Column
    private int noOfDays;

    @Column
    private Date appliedOn;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Leave() {
    }

    public Leave(int id, String category, int noOfDays, Date appliedOn, String description, Status status, User user) {
        this.id = id;
        this.category = category;
        this.noOfDays = noOfDays;
        this.appliedOn = appliedOn;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", category='" + getCategory() + "'" +
                ", noOfDays='" + getNoOfDays() + "'" +
                ", appliedOn='" + getAppliedOn() + "'" +
                ", description='" + getDescription() + "'" +
                ", status='" + getStatus() + "'" +
                ", user='" + getUser() + "'" +
                "}";
    }

}
