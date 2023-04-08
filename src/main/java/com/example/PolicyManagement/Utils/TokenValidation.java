package com.example.PolicyManagement.Utils;

import com.example.PolicyManagement.config.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TokenValidation extends OncePerRequestFilter {
    @Autowired
    private TokenCreation creation;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String auth=request.getHeader("Authorization");
        String token=null;
        String username=null;
        List<GrantedAuthority> authority=new ArrayList<>();
        if(auth!=null && auth.startsWith("Bearer ")){
            token=auth.substring(7);
            username=creation.getUsernameFromToken(token);
            authority.add(new SimpleGrantedAuthority(creation.getAuthorityFromToken(token)));
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            if(creation.isTokenValid(token,username)){
                String pwd= this.customUserDetailService.loadUserByUsername(username).getPassword();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(username,pwd,authority);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
