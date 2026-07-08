package com.example.loom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

import com.example.loom.entity.studySubject;
import com.example.loom.exception.ResourceNotFoundException;
import com.example.loom.repository.studysubjectRepository;

@Service
public class studyService {
    @Autowired
    studysubjectRepository sturepo;

    public studySubject create(studySubject subject) {
        return sturepo.save(subject);
    }

    public List<studySubject> listAll() {
        return sturepo.findAll();
    }

    public studySubject getById(Long id) {
        return sturepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
    }

    public studySubject update(Long id, studySubject subject) {
        studySubject existing = sturepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        existing.setName(subject.getName());
        existing.setDescription(subject.getDescription());
        return sturepo.save(existing);
    }

    public studySubject delete(Long id) {
        studySubject subject = sturepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        sturepo.delete(subject);
        return subject;
    }

    public studySubject patch(Long id, studySubject subject) {
        studySubject existing = sturepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        if (subject.getName() != null)
            existing.setName(subject.getName());

        if (subject.getDescription() != null)
            existing.setDescription(subject.getDescription());

        return sturepo.save(existing);
    }
}
