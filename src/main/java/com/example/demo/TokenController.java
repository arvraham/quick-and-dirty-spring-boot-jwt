
package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class TokenController {
        
    
    private static final String USERNAME = "letme";
    private static final String PASSWORD = "in";

    
    @PostMapping(path = "/token-auth",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity generateToken (@RequestBody Credentials credentials) {
        
        if (credentials != null) {
            
            String username = credentials.getUsername();
            String password = credentials.getPassword();
            
            if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                return ResponseEntity.ok(JwtUtil.generate());
            }
            
        }
        
        return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
        
    }
    
    
    @PostMapping(path = "/token-refresh",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity refreshToken (@RequestBody Token token) {
        
        return (token != null && JwtUtil.isAuthentic(token)) 
                ? ResponseEntity.ok(JwtUtil.generate()) 
                : new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
        
        
    }
    
    
}
