package com.g1t6.backend.Corporate_Pass;

import java.util.ArrayList;
import java.util.List;

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
public class corporate_passController {
    

    @Autowired
    corporate_passService corporate_passService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/test/user/allCorporatePass")
    private List<corporate_pass> getAll(){
        return corporate_passService.getAll();
    }


    
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/test/user/createCorporatePass")
    private String createPass(@RequestBody corporate_passRequestBody jsonBody){
        String status = corporate_passService.createPass(jsonBody);
        String outcome = status.substring(0,1);
        if(outcome.equals("1")){
            return status.substring(1, status.length());
        }else{
            return status;
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/api/test/user/cardLost/{card_Number}")
    private String cardLost(@PathVariable("card_Number") String cardNumber){
        boolean status = corporate_passService.cardLost(cardNumber);
        if(status){
            return "Successfully updated!";
        }

        return "Something went wrong during update";
    }


    


    

}
