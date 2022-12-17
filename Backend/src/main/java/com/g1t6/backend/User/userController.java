package com.g1t6.backend.User;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RestController
public class userController {
    @Autowired
    userService userService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/test/user/userLogin")
    private String login(@RequestBody user user){
        String status = userService.login(user);
        String subString = status.substring(0 , 1);
        if(subString.equals("1")){
            return status.substring(0, status.length());
        }else if(subString.equals("2")){
            return status.substring(0, status.length());
        }else{
            return status.substring(0, status.length());
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/test/user/disable/{id}")
    private String disable(@PathVariable int id){
        String status = userService.disable(id);
        return status;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/test/user/enable/{id}")
    private String enable(@PathVariable int id){
        String status = userService.enable(id);
        return status;
    }
    
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/test/user/allUser")
    private List<user> allUser(){
        return userService.allUser();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/api/test/user/makeAdmin/{id}")
    private String makeAdmin(@PathVariable int id){
        return userService.makeAdmin(id);
        
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/test/user/getAdminStatus/{id}")
    private int getAdminStatus(@PathVariable int id){
        return userService.getAdminStatus(id);
    }
}
