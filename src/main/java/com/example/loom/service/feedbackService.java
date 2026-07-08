package com.example.loom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

import com.example.loom.entity.mentorFeedbacke;
import com.example.loom.exception.BusinessValidationException;
import com.example.loom.exception.ResourceNotFoundException;
import com.example.loom.repository.mentorfeedbackRepository;

@Service
public class feedbackService {
    @Autowired
    mentorfeedbackRepository feedbackRepo;
    public mentorFeedbacke saveData(mentorFeedbacke data) {
        if(data.getRating()<1 || data.getRating()>5)
        {
            throw new BusinessValidationException("Rating must be between 1 and 5");
        }
        return feedbackRepo.save(data);
    }


    public List<mentorFeedbacke> getAllFeedback() {
        return feedbackRepo.findAll();
    }
 
    public mentorFeedbacke getById(Long id) {
        return feedbackRepo.findById(id) .orElseThrow(() ->new ResourceNotFoundException("Feedback not found"));
    }

    public mentorFeedbacke updateFeedback(Long id, mentorFeedbacke data) {

        mentorFeedbacke existing = feedbackRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
       
        if(data.getRating()<1 || data.getRating()>5)
        {
            throw new BusinessValidationException("Rating must be between 1 and 5");
        } 
        existing.setRating(data.getRating());
        existing.setComment(data.getComment());
        existing.setLearnerId(data.getLearnerId());
        existing.setMentorId(data.getMentorId());
        existing.setSessionId(data.getSessionId());

        return feedbackRepo.save(existing);
    }

    public mentorFeedbacke patchUser(Long id, mentorFeedbacke user) {

    mentorFeedbacke existingUser = feedbackRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Feedback not found"));

    if (user.getComment()!= null)
        existingUser.setComment(user.getComment());

    if (user.getLearnerId()!= null)
        existingUser.setLearnerId(user.getLearnerId());

    if (user.getMentorId() != null)
        existingUser.setMentorId(user.getMentorId());

    if (user.getRating() != null)
        existingUser.setRating(user.getRating());
    if (user.getSessionId() != null)
        existingUser.setSessionId(user.getSessionId());

    return feedbackRepo.save(existingUser);
    }

    public mentorFeedbacke deleteFeedback(Long id) {

        mentorFeedbacke existing = feedbackRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("Feedback not found"));

        feedbackRepo.delete(existing);
        return existing;
    }
}

