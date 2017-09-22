
package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class UnauthorizedMessage {
    
    
    public static ResponseEntity response (String message) {
        
        message = (message != null) ? message : "Unauthorized";
        
        return new ResponseEntity(
                new UnauthorizedMessage(message), HttpStatus.UNAUTHORIZED);
        
    }
    

    private String message;


    public UnauthorizedMessage () {}


    public UnauthorizedMessage (String message) {
        this.message = message;
    }

    
    public String getMessage () {
        return message;
    }
    
    
}
