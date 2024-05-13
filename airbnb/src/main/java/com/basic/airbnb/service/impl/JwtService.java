package com.basic.airbnb.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.basic.airbnb.entity.PropertyUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService
{

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expire.duration}")
    private int expireTime;

    private Algorithm algorithm;

    private static final String USER_NAME = "username";

    @PostConstruct
    public void JwtService() {
        // You can initialize the algorithm directly in the constructor
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateJwtToken(PropertyUser propertyUser) {
        return JWT.create()
                .withClaim(USER_NAME, propertyUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }


    public String getUsername(String token)
    {
        DecodedJWT verify = JWT.require(algorithm).withIssuer(issuer).build().verify(token);

        return verify.getClaim(USER_NAME).asString();
    }

}
