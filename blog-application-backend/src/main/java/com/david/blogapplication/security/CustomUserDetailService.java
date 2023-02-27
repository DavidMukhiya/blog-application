package com.david.blogapplication.security;

import com.david.blogapplication.entities.User;
import com.david.blogapplication.exceptions.ResourceNotFoundException;
import com.david.blogapplication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User ", " email : " + username, 0));

        return user;
    }
}
