package com.example.booksecurity.Controller;

import com.example.booksecurity.Entity.Security;
import com.example.booksecurity.Service.JwtService;
import com.example.booksecurity.Service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserCredentialController {
    @Autowired
    private UserCredentialService userCredentialService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public Security registerUser(@RequestBody Security security){
        return userCredentialService.register(security);
    }

    @GetMapping("/validate/token")
    public boolean validateToken(@RequestParam String token){
        return userCredentialService.validateToken(token);
    }
    @PostMapping("/validate/user")
    public String getToken (@RequestBody Security user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),user.getPassword()
                )
        );
        if(authentication.isAuthenticated()){
            return userCredentialService.generateToken(user.getUsername());
        }
        return "User not there";
    }
}