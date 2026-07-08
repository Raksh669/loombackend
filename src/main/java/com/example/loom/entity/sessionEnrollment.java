package com.example.loom.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

@Entity
public class sessionEnrollment {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime enrollmentDate;
    private Boolean feedbackSubmitted;

    @Min(value=1,message="LearnerId must be a positive number starting from 1 ")
    private Long learnerId;
    private Long sessionId;
    public sessionEnrollment() {
    }
    public sessionEnrollment(Long id, LocalDateTime enrollmentDate, Boolean feedbackSubmitted, Long learnerId,
            Long sessionId) {
        this.id = id;
        this.enrollmentDate = enrollmentDate;
        this.feedbackSubmitted = feedbackSubmitted;
        this.learnerId = learnerId;
        this.sessionId = sessionId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }
    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    public Boolean getFeedbackSubmitted() {
        return feedbackSubmitted;
    }
    public void setFeedbackSubmitted(Boolean feedbackSubmitted) {
        this.feedbackSubmitted = feedbackSubmitted;
    }
    public Long getLearnerId() {
        return learnerId;
    }
    public void setLearnerId(Long learnerId) {
        this.learnerId = learnerId;
    }
    public Long getSessionId() {
        return sessionId;
    }
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
    
}
