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

import com.example.loom.entity.studySubject;
import com.example.loom.service.studyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subjects")
public class studyController {
    @Autowired
     studyService stuser;

    @PostMapping("/create")
    public studySubject create(@Valid @RequestBody studySubject subject) {
        return stuser.create(subject);
    }

    @GetMapping("/all")
    public List<studySubject> listAll() {
        return stuser.listAll();
    }

    @GetMapping("/get/{id}")
    public studySubject getById(@PathVariable Long id)
    {
        return stuser.getById(id);
    }

    @PutMapping("/update/{id}")
    public studySubject update(@PathVariable Long id,@RequestBody studySubject subject) {
        return stuser.update(id, subject);
    }

    @DeleteMapping("/delete/{id}")
    public studySubject delete(@PathVariable Long id)
    {
        return stuser.delete(id);
    }
    
    @PatchMapping("/patch/{id}")
    public studySubject patch(@PathVariable Long id, @RequestBody studySubject subject) {
        return stuser.patch(id, subject);
    }
}
