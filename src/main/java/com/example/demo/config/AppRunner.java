package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role role1 = new Role();
        role1.setRoleName("ADMIN");

        Role role2 = new Role();
        role2.setRoleName("USER");

        Role savedRole1 = roleRepo.save(role1);
        Role savedRole2 = roleRepo.save(role2);

        User user1 = new User();
        user1.setEmail("g@gmail.com");
        user1.setPass("123");
        user1.setRole(savedRole1);

        User user2 = new User();
        user2.setEmail("n@gmail.com");
        user2.setPass("456");
        user2.setRole(savedRole2);

        User user3 = new User();
        user3.setEmail("m@gmail.com");
        user3.setPass("65");
        user3.setRole(savedRole2);

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);

    }

}
