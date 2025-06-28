package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Leave;
import com.example.demo.model.LeaveRequest;
import com.example.demo.model.Role;
import com.example.demo.model.Status;
import com.example.demo.model.User;
import com.example.demo.repo.LeaveRepo;
import com.example.demo.repo.UserRepo;

@Service
public class LeaveService {

    @Autowired
    LeaveRepo leaveRepo;

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<Object> addLeaveRequest(String userName, LeaveRequest leaveRequest) {
        try {
            Optional<User> userOptn = getUserFromUserName(userName);
            if (userOptn.isEmpty()) {
                return ResponseEntity.status(404).body("User Not Found :Leave Service");
            }
            User userDb = userOptn.get();
            Role role = userDb.getRole();

            if (!role.getRoleName().equals("USER")) {
                return ResponseEntity.status(401).body("Unauthorized :Leave Service");
            }

            Leave leave = new Leave();
            leave.setAppliedOn(leaveRequest.getAppliedOn());
            leave.setDescription(leaveRequest.getDescription());
            leave.setCategory(leaveRequest.getCategory());
            leave.setNoOfDays(leaveRequest.getNoOfDays());
            leave.setStatus(Status.PENDING);
            leave.setUser(userDb);
            Leave savedLeave = leaveRepo.save(leave);

            return ResponseEntity.ok(savedLeave);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error in Server" + e.getMessage());
        }
    }

    public ResponseEntity<Object> updateUserComment(String userName, int leaveId, String comment) {
        try {
            Optional<User> userOptn = getUserFromUserName(userName);
            if (userOptn.isEmpty()) {
                return ResponseEntity.status(404).body("User Not Found :Leave Service");
            }
            User userDb = userOptn.get();
            Role role = userDb.getRole();

            if (!role.getRoleName().equals("USER")) {
                return ResponseEntity.status(401).body("Unauthorized :Leave Service");
            }

            Optional<Leave> leaveOptn = leaveRepo.findById(leaveId);
            if (leaveOptn.isEmpty()) {
                return ResponseEntity.status(401).body("Leave does not exist :Leave Service");
            }
            Leave leave = leaveOptn.get();
            User user = leave.getUser();

            if (!user.getEmail().equals(userDb.getEmail())) {
                return ResponseEntity.status(401).body("You dont own this leave :Leave Service");
            }

            leave.setDescription(comment);
            Leave savedLeave = leaveRepo.save(leave);

            return ResponseEntity.ok(savedLeave);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error in Server" + e.getMessage());
        }
    }

    public ResponseEntity<Object> getLeaves(String userName) {
        try {
            Optional<User> userOptn = getUserFromUserName(userName);
            if (userOptn.isEmpty()) {
                return ResponseEntity.status(404).body("User Not Found :Leave Service");
            }
            User userDb = userOptn.get();
            Role role = userDb.getRole();

            if (role.getRoleName().equals("ADMIN")) {
                return ResponseEntity.ok(leaveRepo.findAll());
            } else if (role.getRoleName().equals("USER")) {
                List<Leave> customLeave = leaveRepo.findByUserEmail(userName);
                return ResponseEntity.ok(customLeave);
            } else {
                return ResponseEntity.status(401).body("Unauthorized :Leave Service");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error in Server" + e.getMessage());
        }
    }

    public ResponseEntity<Object> updateLeaveStatus(String userName, int leaveId, String status) {

        try {
            Optional<User> userOptn = getUserFromUserName(userName);
            if (userOptn.isEmpty()) {
                return ResponseEntity.status(404).body("User Not Found :Leave Service");
            }
            User userDb = userOptn.get();
            Role role = userDb.getRole();

            if (!role.getRoleName().equals("ADMIN")) {
                return ResponseEntity.status(401).body("Unauthorized :Leave Service");
            }

            Optional<Leave> leaveOptn = leaveRepo.findById(leaveId);
            if (leaveOptn.isEmpty()) {
                return ResponseEntity.status(401).body("Leave does not exist :Leave Service");
            }
            Leave leave = leaveOptn.get();

            leave.setStatus(Status.valueOf(status.toUpperCase()));
            leaveRepo.save(leave);

            return ResponseEntity.ok("Leave status updated" + status.toUpperCase());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error in Server" + e.getMessage());
        }
    }

    public ResponseEntity<Object> deleteLeave(String userName, int id) {
        try {
            Optional<User> userOptn = getUserFromUserName(userName);
            if (userOptn.isEmpty()) {
                return ResponseEntity.status(404).body("User Not Found :Leave Service");
            }
            User userDb = userOptn.get();
            Role role = userDb.getRole();

            if (!role.getRoleName().equals("ADMIN")) {
                return ResponseEntity.status(401).body("Unauthorized :Leave Service");
            }

            Optional<Leave> leaveOptn = leaveRepo.findById(id);
            if (leaveOptn.isEmpty()) {
                return ResponseEntity.status(401).body("Leave does not exist :Leave Service");
            }
            Leave leave = leaveOptn.get();

            leaveRepo.delete(leave);

            return ResponseEntity.ok("leave deleted successfully");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error in Server" + e.getMessage());
        }
    }

    public ResponseEntity<Object> getLeavesByNoOfDays(String userName, int noOfDays) {
        try {
            Optional<User> userOptn = getUserFromUserName(userName);
            if (userOptn.isEmpty()) {
                return ResponseEntity.status(404).body("User Not Found :Leave Service");
            }
            User userDb = userOptn.get();
            Role role = userDb.getRole();

            if (!role.getRoleName().equals("ADMIN")) {
                return ResponseEntity.status(401).body("Unauthorized :Leave Service");
            }

            List<Leave> leaveCond = leaveRepo.findByNumberOfDaysGreaterThan(noOfDays);
            Set<String> userNames = new HashSet<>();
            for (Leave leave : leaveCond) {
                userNames.add(leave.getUser().getEmail());
            }

            return ResponseEntity.ok(userNames);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error in Server" + e.getMessage());
        }
    }

    public Optional<User> getUserFromUserName(String emailId) {
        return userRepo.findByEmail(emailId);
    }

}
