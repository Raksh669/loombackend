package com.example.loom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loom.entity.studySubject;

@Repository
public interface studysubjectRepository extends JpaRepository<studySubject,Long> {

}
