package com.example.loom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

import com.example.loom.entity.sessionEnrollment;
import com.example.loom.exception.ResourceNotFoundException;
import com.example.loom.repository.sessionenrollmentRepository;

@Service
public class enrollmentWorkflowService {
    @Autowired
    sessionenrollmentRepository enrollrepo;

    public sessionEnrollment create(sessionEnrollment enrollment) {
        return enrollrepo.save(enrollment);
    }

    public List<sessionEnrollment> getAll() {
        return enrollrepo.findAll();
    }

    public sessionEnrollment getById(Long id) {
        return enrollrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
    }
    
    public sessionEnrollment update(Long id, sessionEnrollment enrollment) {
        sessionEnrollment existing = enrollrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        existing.setEnrollmentDate(enrollment.getEnrollmentDate());
        existing.setFeedbackSubmitted(enrollment.getFeedbackSubmitted());
        existing.setLearnerId(enrollment.getLearnerId());
        existing.setSessionId(enrollment.getSessionId());

        return enrollrepo.save(existing);
    }

    public sessionEnrollment delete(Long id) {
        sessionEnrollment existing = enrollrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        enrollrepo.delete(existing);
        return existing;
    }

    public sessionEnrollment patch(Long id, sessionEnrollment enrollment) {
        sessionEnrollment existing = enrollrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        if (enrollment.getEnrollmentDate() != null)
            existing.setEnrollmentDate(enrollment.getEnrollmentDate());

        existing.setFeedbackSubmitted(enrollment.getFeedbackSubmitted());

        if (enrollment.getLearnerId() != null)
            existing.setLearnerId(enrollment.getLearnerId());

        if (enrollment.getSessionId() != null)
            existing.setSessionId(enrollment.getSessionId());

        return enrollrepo.save(existing);
    }
}
