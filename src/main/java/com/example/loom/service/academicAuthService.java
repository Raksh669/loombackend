package com.example.loom.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loom.entity.academicUser;
import com.example.loom.exception.ResourceNotFoundException;
import com.example.loom.repository.academicuserRepository;

@Service
public class academicAuthService {

    private final PasswordEncoder encoder;
    private final academicuserRepository repo;

    public academicAuthService(PasswordEncoder encoder, academicuserRepository repo) {
        this.encoder = encoder;
        this.repo = repo;
        
    }

   
    public academicUser register(academicUser user) {

        user.setPassword(encoder.encode(user.getPassword()));

        return repo.save(user);
    }

   
    public List<academicUser> getAllUsers() {
        return repo.findAll();
    }

   
    public academicUser getUserById(Long id) {

        return repo.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found"));
    }

   
    public academicUser updateUser(Long id, academicUser user) {

        academicUser existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existing.setFullName(user.getFullName());
        existing.setEmail(user.getEmail());
        existing.setPassword(encoder.encode(user.getPassword()));

        existing.setDepartment(user.getDepartment());
        existing.setBio(user.getBio());
        existing.setRole(user.getRole());

        return repo.save(existing);
    }


    public academicUser deleteUser(Long id) {

        academicUser user = repo.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found"));

        repo.delete(user);

        return user;
    }

    public academicUser patchUser(Long id, academicUser user) {

        academicUser existing = repo.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found"));

        if (user.getFullName() != null)
            existing.setFullName(user.getFullName());

        if (user.getEmail() != null)
            existing.setEmail(user.getEmail());

        if (user.getPassword() != null)
            existing.setPassword(encoder.encode(user.getPassword()));

        if (user.getDepartment() != null)
            existing.setDepartment(user.getDepartment());

        if (user.getBio() != null)
            existing.setBio(user.getBio());

        if (user.getRole() != null)
            existing.setRole(user.getRole());

        return repo.save(existing);
    }

   
    public boolean verifyPassword(String email, String password) {

        academicUser user = repo.findByEmail(email).orElseThrow(() ->new ResourceNotFoundException("User not found"));

        return encoder.matches(password, user.getPassword());
    }
}