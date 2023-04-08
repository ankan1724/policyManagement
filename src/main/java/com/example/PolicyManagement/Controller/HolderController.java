package com.example.PolicyManagement.Controller;

import com.example.PolicyManagement.Entity.Holders;
import com.example.PolicyManagement.Repositories.HolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/holder")
public class HolderController {

    @Autowired
    private HolderRepo holderRepo;

    @PostMapping("/add")
    public String add(@RequestParam long holderId,@RequestParam  long contact,@RequestParam  String name,
                       @RequestParam String email,@RequestParam  String address,@RequestParam  String gender){
        Holders holders=new Holders(holderId,contact,name,email,address,gender);
        this.holderRepo.save(holders);
        return "Application created";
    }
}
