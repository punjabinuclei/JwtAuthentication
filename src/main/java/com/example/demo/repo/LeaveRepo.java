package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Leave;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Integer> {

    List<Leave> findByUserEmail(String email);

    List<Leave> findBynoOfDaysGreaterThan(int noOfDays);


}
