package com.g1t6.backend.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class constantService {
    
    @Autowired
    constantRepo constantRepo;


    public boolean changeMaxLoan(constant constant){
        int max_loan_per_month = constant.getMaxLoanPerMonth();
        constantRepo.save(new constant(1 , max_loan_per_month));
        return true;
    }
    
    public List<constant> getMaxLoanPerMonth(){
        List<constant> returnList = new ArrayList<>();

        constantRepo.findAll().forEach(item -> returnList.add(item));

        return returnList;
    }
}
