package com.hms.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    //it comes from jwt token library
    private Algorithm algorithm;

    //this annotation comes from hibernate
    //it will help us to run the method automatically when the projects starts

    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
        //HMAC256 is the algorithm and it uses the algorithmkey that we have set in properties to generate the token
        //the algorithm ask for a exception to throw in the method
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    //computer engineer is unemployed
    public String generateToken(String username) {
        return JWT.create()
                .withClaim("name", username)
                //below code will pick the starting time t0 24 hrs from the time of signIn
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }


    //withIssuer takes issuer and check if the same issuer is generating the token
    public String getUsername(String token) {
        DecodedJWT decodedJWT = JWT.
                require(algorithm).
                withIssuer(issuer)
                .build()
                .verify(token);
        return decodedJWT.getClaim("name").asString();

    }

}
