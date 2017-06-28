package com.urban.skelonwar.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hernan.urban on 6/13/2017.
 */
public class TokenAuthenticationService {

    private static final long EXPIRATION_TIME =  86400000; //1 day
    private static final String SECRET = "thisisatokensecret";
    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";


    public static void addAuthentication(HttpServletResponse res, String username){
        String jwt = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
    }

    public static Authentication getAuthentication(HttpServletRequest req){
        String token = req.getHeader(HEADER_STRING);
        if (null != token){
            String username = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject();
            return null != username ? new UsernamePasswordAuthenticationToken(username, null, new ArrayList<GrantedAuthority>()):null;
        }
        return null;
    }
}