package com.example.demo.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LeaveRequest;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @PostMapping("/user")
    public RequestEntity<Object> addLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        return null;
    }

    @PutMapping("/comment")
    public RequestEntity<Object> updateUserComment(@RequestParam int leaveId, @RequestParam String comment) {

        return null;
    }

    @GetMapping("/user")
    public RequestEntity<Object> getLeaves() {
        return null;
    }

    @PutMapping("/status")
    public RequestEntity<Object> updateLeaveStatus(@RequestParam int leaveId, @RequestParam String status) {

        return null;
    }

    @DeleteMapping("/{id}")
    public RequestEntity<Object> deleteLeave(@PathVariable int id) {
        return null;
    }

    @GetMapping("/days")
    public RequestEntity<Object> getLeavesByNoOfDays(@RequestParam int noOfDays) {
        return null;
    }

}
