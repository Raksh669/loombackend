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


import com.example.loom.entity.mentorFeedbacke;
import com.example.loom.service.feedbackService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/feedback")
public class feedbackController {
    @Autowired
    feedbackService feedservice;
   @PostMapping("/post")
    public mentorFeedbacke saveData(@Valid @RequestBody mentorFeedbacke data) {
        return feedservice.saveData(data);
    }

    @GetMapping("/getFeedback")
    public List<mentorFeedbacke> getData() {
        return feedservice.getAllFeedback();
    }
    
    @GetMapping("/get/{id}")
    public mentorFeedbacke getUserData(@PathVariable Long id)
    {
        return feedservice.getById(id);
    }

    @PutMapping("/put/{id}")
    public mentorFeedbacke updateData(@PathVariable Long id,@RequestBody mentorFeedbacke data) {
        return feedservice.updateFeedback(id, data);
    }


    @PatchMapping("/patch/{id}")
    public mentorFeedbacke patchUser(@PathVariable Long id,@RequestBody mentorFeedbacke user)
    {

        return feedservice.patchUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public mentorFeedbacke deleteData(@PathVariable Long id)
    {
        return feedservice.deleteFeedback(id);
    }
}



    
    