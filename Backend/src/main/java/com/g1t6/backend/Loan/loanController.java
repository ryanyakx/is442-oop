package com.g1t6.backend.Loan;

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
public class loanController {
    

    @Autowired
    loanService loanService;

    

    
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/test/user/getAllLoan")
    private List<loan> getAll(){
        return loanService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/test/user/createLoan")
    private String createLoan(@RequestBody loanRequestBody jsonBody){
        String status = loanService.createLoan(jsonBody);
        String subString = status.substring(0 , 1);
        if(subString.equals("a")){
            return status.substring(0, status.length());
        }else if(subString.equals("b")){
            return status.substring(0, status.length());
        }else if(subString.equals("c")){
            return status.substring(0, status.length());
        }else{
            return status;
        }
    }
    
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/api/test/user/updateLoanRecord/{id}/{state}")
    private String updateRecord(@PathVariable int id , @PathVariable int state){
        String status = loanService.updateRecord(id, state);
        String subString = status.substring(0 , 1);
        if(subString.equals("a")){
            return status.substring(0, status.length());
        }else if(subString.equals("b")){
            return status.substring(0, status.length());
        }else if(subString.equals("c")){
            return status.substring(0, status.length());
        }else if(subString.equals("d")){
            return status.substring(0, status.length());
        }else if(subString.equals("e")){
            return status.substring(0, status.length());
        }else{
            return status.substring(0, status.length());
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/test/user/getLoanDate/{date}")
    private List<loan> retrieveLoanDate(@PathVariable String date){
        return loanService.retrieveLoanDate(date); 
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/test/user/getLoanByEmployee/{id}")
    private List<loan> getLoanByEmployee(@PathVariable int id){
        return loanService.getLoanByE(id);
    }

}
