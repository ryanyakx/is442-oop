package com.g1t6.backend.User;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class userService {
    @Autowired
    userRepo userRepo;

    public String login(user user) {
        int id = user.getEmployeeID();
        String name = user.getName();
        String email = user.getEmail();
        int contact_Num = user.getContactNum();
        String password = user.getPassword();

        if (!userRepo.findById(id).isPresent()) {
            int status = 1;
            int admin = 1;
            userRepo.save(new user(id, name, email, contact_Num, password, status, admin));

            return "3Login Successful!";
        } else {
            if (userRepo.findById(id).get().getStatus() == 0) {
                return "1User does not exist!";
            } else {
                return "2Login Successful!";
            }
        }

    }



    public String disable(int id) {
        user user = userRepo.findById(id).get();
        String name = user.getName();
        String email = user.getEmail();
        int num = user.getContactNum();
        String password = user.getPassword();
        int is_admin = user.getAdminStatus();
        int status = 0;
        userRepo.save(new user(id, name, email, num, password, status ,is_admin));

        return "Successfully disabled the user";
    }

    public List<user> allUser(){
        List<user> all_user = new ArrayList<>();
        userRepo.findAll().forEach(user -> all_user.add(user));
        return all_user;
    }
    
    public String makeAdmin(int id){
        user user = userRepo.findById(id).get();
        int user_id = user.getEmployeeID();
        String name = user.getName();
        String email = user.getEmail();
        int num = user.getContactNum();
        String password = user.getPassword();
        int status = user.getStatus();
        userRepo.save(new user(user_id, name, email, num, password, status, 2));
        return "Successfully assigned as an Administrator";
    }

    public String enable(int id){
        user user = userRepo.findById(id).get();
        int user_id = user.getEmployeeID();
        String name = user.getName();
        String email = user.getEmail();
        int num = user.getContactNum();
        String password = user.getPassword();
        int is_admin = user.getAdminStatus();
        userRepo.save(new user(user_id, name, email, num, password, 1 ,is_admin));
        return "Successfully enabled the user";
    }

    public int getAdminStatus(int id){
        user user = userRepo.findById(id).get();
        return user.getAdminStatus();
    }
}
