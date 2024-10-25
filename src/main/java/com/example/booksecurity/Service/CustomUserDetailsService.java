package com.example.booksecurity.Service;

import com.example.booksecurity.Entity.Security;
import com.example.booksecurity.Repository.SecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SecurityRepo securityRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Security> user = securityRepo.findByUsername(username);
        return user.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("no username"));

    }
}
