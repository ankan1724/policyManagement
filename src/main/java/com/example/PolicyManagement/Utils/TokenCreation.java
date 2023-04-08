package com.example.PolicyManagement.Utils;

import com.example.PolicyManagement.Repositories.HolderRepo;
import com.example.PolicyManagement.Repositories.UserRepo;
import com.example.PolicyManagement.config.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenCreation {


    private static String SECRET="$2a$15$IH6qDtXHfy.qCw.62Yv15us.bgNM6m1vNsDAU2YF64GJXLw2E3vIS";

    @Autowired
    public UserRepo repo;
    @Autowired
    private HolderRepo holderRepo;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1800000))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", repo.findByUsername(username).get(0).getUsername());
        claims.put("authority", repo.findByUsername(username).get(0).getAuthority());
        int holder_id = this.repo.findByUsername(username).get(0).getId();
        String sub ="ankan";
        return createToken(claims, sub);
    }

    public Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return (String) getAllClaims(token).get("username");
    }

    public String getAuthorityFromToken(String token) {
        return (String) getAllClaims(token).get("authority");
    }

    public Date getExpirationTimeFromToken(String token) {
        return getAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        Date date = new Date(System.currentTimeMillis());
        return getExpirationTimeFromToken(token).before(date);
    }

    public boolean isTokenValid(String token, String username) {
        String userName = customUserDetailService.loadUserByUsername(username).getUsername();
        return (getUsernameFromToken(token).equals(userName) && (!isTokenExpired(token)));
    }
}


