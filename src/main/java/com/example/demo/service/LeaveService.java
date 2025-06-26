package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.LeaveRequest;
import com.example.demo.repo.LeaveRepo;

@Service
public class LeaveService {

    @Autowired
    LeaveRepo leaveRepo;

    public RequestEntity<Object> addLeaveRequest(LeaveRequest leaveRequest) {
        return null;
    }

    public RequestEntity<Object> updateUserComment(int leaveId, String comment) {

        return null;
    }

    public RequestEntity<Object> getLeaves() {
        return null;
    }

    public RequestEntity<Object> updateLeaveStatus(int leaveId, String status) {

        return null;
    }

    public RequestEntity<Object> deleteLeave(int id) {
        return null;
    }

    public RequestEntity<Object> getLeavesByNoOfDays(int noOfDays) {
        return null;
    }

}
