package com.example.PolicyManagement;

import com.example.PolicyManagement.Entity.Users;
import com.example.PolicyManagement.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class PolicyManagementApplication {

    @Autowired
    UserRepo repo;

    public static void main(String[] args) {
        SpringApplication.run(PolicyManagementApplication.class, args);
    }

    @PostConstruct
    public void initUser() {
        List<Users> usersList = Stream.of(
                new Users(10503, "ankan.G@tcs.com", new BCryptPasswordEncoder().encode("ankan"), "view"),
                new Users(10504, "mary.watsonG@tcs.com", new BCryptPasswordEncoder().encode("mary"), "view"),
                new Users(10506, "john.doe.G@tcs.com", new BCryptPasswordEncoder().encode("doe"), "view"),
                new Users(10509, "peter.parkerG@tcs.com", new BCryptPasswordEncoder().encode("parker"), "view")).collect(Collectors.toList());
        repo.saveAll(usersList);
    }
}
