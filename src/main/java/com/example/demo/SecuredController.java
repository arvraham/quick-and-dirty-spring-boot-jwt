
package com.example.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
public class SecuredController {
    
    
    @GetMapping(path = "/secret-stuff")
    public ResponseEntity getSecretMessage (
            @RequestHeader("Authorization") String authorizationHeader) {
                
        return (JwtUtil.isAuthenticAuthorizationHeader(
                authorizationHeader, "Bearer ")) 
                    ? ResponseEntity.ok("I like Trains")
                    : UnauthorizedMessage.response(null);
        
    }
    
}
