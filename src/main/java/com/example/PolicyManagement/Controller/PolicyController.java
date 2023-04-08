package com.example.PolicyManagement.Controller;

import com.example.PolicyManagement.Entity.Holders;
import com.example.PolicyManagement.Entity.PolicyEntity;
import com.example.PolicyManagement.Repositories.PolicyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyRepo repo;

    @PostMapping("/add")
    public PolicyEntity add(@RequestParam long policynumber,@RequestParam long contact,@RequestParam String type,
                            @RequestParam double amount, @RequestParam int tenure, @RequestParam double premium,
                            @RequestParam Holders holders){
        PolicyEntity policyEntity=new PolicyEntity(policynumber,contact,type,amount,tenure,premium,holders);
        return this.repo.save(policyEntity);
    }

    @GetMapping("/fetch")
    public Optional<PolicyEntity> getPolicy(@RequestParam long policynumber) {
        return this.repo.findById(policynumber);

    }

    @GetMapping("/fetch/{contact}")
    public Optional<PolicyEntity> getPolicybyContact(@PathVariable long contact) {
        return this.repo.findByContact(contact);

    }

}
