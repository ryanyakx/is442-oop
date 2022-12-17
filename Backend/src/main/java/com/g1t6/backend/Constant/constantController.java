package com.g1t6.backend.Constant;

import com.g1t6.backend.Corporate_Pass.corporate_pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RestController
public class constantController {

    @Autowired
    constantService constantService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/test/user/changeMaxLoan")
    private String changeMaxLoan(@RequestBody constant constant){
        boolean status = constantService.changeMaxLoan(constant);
        if(status == true){
            return "Successfully Updated!";
        }else{
            return "Something went wrong!";
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/test/user/getMaxLoanPerMonth")
    private List<constant> getMaxLoanPerMonth(){
        return constantService.getMaxLoanPerMonth();
    }


}
