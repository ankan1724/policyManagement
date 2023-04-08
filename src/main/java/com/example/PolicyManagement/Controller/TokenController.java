package com.example.PolicyManagement.Controller;

import com.example.PolicyManagement.Entity.AuthRequest;
import com.example.PolicyManagement.Utils.TokenCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class TokenController {

    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public TokenCreation creation;

    @PostMapping("/token")
    public Map<String, Object> getToken(@RequestBody AuthRequest request) throws AuthenticationException {
        Map<String,Object> result=new HashMap<>();
        Authentication auth=manager.authenticate(new
                UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        if(auth.isAuthenticated()){
            result.put("token",creation.generateToken(request.getUsername()));
            result.put("type","Bearer");
            return result;
        }
        else {
            throw new AuthenticationException("invalid user");
        }
    }
}
