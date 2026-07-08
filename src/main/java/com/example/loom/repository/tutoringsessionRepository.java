package com.example.loom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loom.entity.tutoringSession;

@Repository 
public interface tutoringsessionRepository extends JpaRepository<tutoringSession,Long>{

}
