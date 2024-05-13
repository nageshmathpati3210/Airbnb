package com.basic.airbnb.configuration;

import com.basic.airbnb.entity.PropertyUser;
import com.basic.airbnb.repository.PropertyUserRepository;
import com.basic.airbnb.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTRequestFilter  extends OncePerRequestFilter
{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PropertyUserRepository propertyUserRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String authorization = request.getHeader("Authorization");
        if(authorization !=null && authorization.startsWith("Bearer "))
        {
            String substring = authorization.substring(8, authorization.length()-1);
            String username = jwtService.getUsername(substring);
            Optional<PropertyUser> byUsername = propertyUserRepository.findByUsername(username);
            if(byUsername.isPresent())
            {
                PropertyUser propertyUser = byUsername.get();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(propertyUser,null, Collections.singleton(new SimpleGrantedAuthority(propertyUser.getUserRole())));
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

        filterChain.doFilter(request,response);

    }
}
