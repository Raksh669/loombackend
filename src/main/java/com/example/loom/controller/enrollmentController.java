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

import com.example.loom.entity.sessionEnrollment;
import com.example.loom.service.enrollmentWorkflowService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enrollments")
public class enrollmentController {
    @Autowired
    enrollmentWorkflowService enrollservice;

    @PostMapping("/post")
    public sessionEnrollment create(@Valid @RequestBody sessionEnrollment enrollment) {
        return enrollservice.create(enrollment);
    }

    @GetMapping("/get")
    public List<sessionEnrollment> getAll() {
        return enrollservice.getAll();
    }


    @GetMapping("/get/{id}")   
    public sessionEnrollment getById(@PathVariable Long id)
    {
        return enrollservice.getById(id);
    }

    @PutMapping("/put/{id}")
    public sessionEnrollment update(@PathVariable Long id,@RequestBody sessionEnrollment enrollment) {
        return enrollservice.update(id, enrollment);
    }

    @DeleteMapping("/delete/{id}")
    public sessionEnrollment delete(@PathVariable Long id)
    {
        return enrollservice.delete(id);
    }  

    @PatchMapping("/patch/{id}")
    public sessionEnrollment patch(@PathVariable Long id,@RequestBody sessionEnrollment enrollment) {
        return enrollservice.patch(id, enrollment);
    }
}
