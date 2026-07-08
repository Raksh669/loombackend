package com.example.loom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loom.entity.tutoringSession;
import com.example.loom.service.sessionManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sessions")
public class sessionController {
    @Autowired
    sessionManagementService service;
    @PostMapping("/post")
    public tutoringSession create(@Valid @RequestBody tutoringSession session) {
        return service.create(session);
    }

    @GetMapping("/get")
    public List<tutoringSession> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public tutoringSession getById(@PathVariable Long id)
    {
        return service.getById(id);
    }

    @PutMapping("/put/{id}")
    public tutoringSession update(@PathVariable Long id,@RequestBody tutoringSession session) {
        return service.update(id, session);
    }

    @PatchMapping("/patch/{id}")
    public tutoringSession patch(@PathVariable Long id,@RequestBody tutoringSession session) {
        return service.patch(id, session);
    }

    @DeleteMapping("/delete/{id}")
    public tutoringSession delete(@PathVariable Long id)
    {
        return service.delete(id);
    }
}
