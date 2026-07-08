package com.example.loom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.loom.entity.academicUser;
import com.example.loom.service.JwtService;
import com.example.loom.service.academicAuthService;

import jakarta.validation.Valid;  

@RestController
@RequestMapping("/api/auth")
public class authController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final academicAuthService authService;

    public authController(JwtService jwtService,AuthenticationManager authenticationManager,academicAuthService authService) {

        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody academicUser user) {
        try{
            academicUser users=authService.register(user);
            return ResponseEntity.ok(users);
        }
        catch(Exception e)
        {
            Map<String,String> errStmt=new HashMap<>();
            if(e.getMessage()!=null && e.getMessage().toLowerCase().contains("duplicate"))
            {
                errStmt.put("message","Email already registered");
            }
            else{
                errStmt.put("message","Registration Failed!"+e.getMessage());
            }
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errStmt);
        }
    }
 

   
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody academicUser user) {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
            String token = jwtService.generateToken(user.getEmail());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login Successful");
            response.put("token", token);
            return ResponseEntity.ok(response);
        } 
        catch (BadCredentialsException e) {

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid Email or Password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

  
    @GetMapping("/get")
   // @PreAuthorize("hasRole('ACADEMIC_ADMIN')","hasRole('SUPPORT_AGENT')")
    public List<academicUser> getAllUsers() {
        return authService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public academicUser getUserById(@PathVariable Long id) {
        return authService.getUserById(id);
    }


    @PutMapping("/put/{id}")
    public academicUser updateUser(@PathVariable Long id, @RequestBody academicUser user) {
        return authService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public academicUser deleteUser(@PathVariable Long id) {
        return authService.deleteUser(id);
    }

    @PatchMapping("/patch/{id}")
    public academicUser patchUser(@PathVariable Long id, @RequestBody academicUser user) {
        return authService.patchUser(id, user);
    }
} 

