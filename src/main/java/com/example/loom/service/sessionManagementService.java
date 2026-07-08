package com.example.loom.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

import com.example.loom.entity.tutoringSession;
import com.example.loom.exception.BusinessValidationException;
import com.example.loom.exception.ResourceNotFoundException;
import com.example.loom.repository.tutoringsessionRepository;

@Service
public class sessionManagementService {
    @Autowired
    tutoringsessionRepository sessionrepo;
    public tutoringSession create(tutoringSession session) {

        if (session.getStartTime() == null || session.getStartTime().isBefore(LocalDateTime.now())) {
            throw new BusinessValidationException("Invalid start time");
        }
        session.setCurrentEnrollment(0);
        session.setStatus("SCHEDULED");

        return sessionrepo.save(session);
    }

    public List<tutoringSession> getAll() {
        return sessionrepo.findAll();
    }

    public tutoringSession getById(Long id) {
        return sessionrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session not found"));
    }

    public tutoringSession update(Long id, tutoringSession session) {

        tutoringSession existing = sessionrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session not found"));

        existing.setTitle(session.getTitle());
        existing.setDescription(session.getDescription());
        existing.setStartTime(session.getStartTime());
        existing.setEndTime(session.getEndTime());
        existing.setMaxCapacity(session.getMaxCapacity());
        existing.setCurrentEnrollment(session.getCurrentEnrollment());
        existing.setStatus(session.getStatus());
        existing.setMentorId(session.getMentorId());
        existing.setSubjectId(session.getSubjectId());

        return sessionrepo.save(existing);
    }

    public tutoringSession patch(Long id, tutoringSession session) {

        tutoringSession existing = sessionrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session not found"));

        if (session.getTitle() != null)
            existing.setTitle(session.getTitle());

        if (session.getDescription() != null)
            existing.setDescription(session.getDescription());

        if (session.getStartTime() != null)
            existing.setStartTime(session.getStartTime());

        if (session.getEndTime() != null)
            existing.setEndTime(session.getEndTime());

        if (session.getMaxCapacity() != null)
            existing.setMaxCapacity(session.getMaxCapacity());

        if (session.getCurrentEnrollment() != null)
            existing.setCurrentEnrollment(session.getCurrentEnrollment());

        if (session.getStatus() != null)
            existing.setStatus(session.getStatus());

        if (session.getMentorId() != null)
            existing.setMentorId(session.getMentorId());

        if (session.getSubjectId() != null)
            existing.setSubjectId(session.getSubjectId());

        return sessionrepo.save(existing);
    }

    public tutoringSession delete(Long id) {

        tutoringSession existing = sessionrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session not found"));

        sessionrepo.delete(existing);
        return existing;
    }

}

