package com.example.loom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.loom.entity.academicUser;
import com.example.loom.exception.ResourceNotFoundException;
import com.example.loom.repository.academicuserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    academicuserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        academicUser user = repo.findByEmail(email).orElseThrow(() ->new ResourceNotFoundException("User Not Found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}