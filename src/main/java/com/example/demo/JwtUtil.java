
package com.example.demo;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.time.Instant;
import java.util.Date;


public class JwtUtil {
    
    
    private static final String SECRET = "secret";
    private static final long SECONDS_TO_LIVE = 30;
    
    
    public static Token generate () {
        
        return new Token(Jwts.builder()
                        .setExpiration(
                                Date.from(Instant.now()
                                        .plusSeconds(SECONDS_TO_LIVE)))
                        .setIssuedAt(Date.from(Instant.now()))
                        .signWith(SignatureAlgorithm.HS512, SECRET)
                        .compact());
        
    }
    
    
    public static boolean isAuthenticAuthorizationHeader (
            String header,
            String prefix) { 
        
        if (prefix == null || prefix.trim().isEmpty()) {
            throw new IllegalArgumentException("Prefix for authorization header"
                    + " inspection may neither be null nor empty");
        }
        
        if (header != null && header.startsWith(prefix)) {
            return isAuthenitc(header.substring(prefix.length()));
        }
        
        return false;
    }
    
    
    public static boolean isAuthentic (Token token) {
        return isAuthenitc(token.getToken());
    }
    
    
    private static boolean isAuthenitc (String token) {
        
        try {
            Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
            return true;

        } catch (SignatureException e) {
            return false;
        }
        
    }

    
    private JwtUtil () {}    
    
    
}
