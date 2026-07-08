package com.example.loom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class mentorFeedbacke{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Integer rating;

    @NotBlank(message="Comment cannot be blank")
    @Size(min=20,max=100,message ="Comment must be 20 to 100 characters")
    private String comment;

    @Min(value=1,message="LearnerId must be a positive number starting from 1 ")
    private Long learnerId;
    private Long mentorId;
    private Long sessionId;
    public mentorFeedbacke() {
    }
    public mentorFeedbacke(Long id, Integer rating, String comment, Long learnerId, Long mentorId, Long sessionId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.learnerId = learnerId;
        this.mentorId = mentorId;
        this.sessionId = sessionId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Long getLearnerId() {
        return learnerId;
    }
    public void setLearnerId(Long learnerId) {
        this.learnerId = learnerId;
    }
    public Long getMentorId() {
        return mentorId;
    }
    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }
    public Long getSessionId() {
        return sessionId;
    }
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
    
}
