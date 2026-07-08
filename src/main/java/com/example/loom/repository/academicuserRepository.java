package com.example.loom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loom.entity.academicUser;

@Repository
public interface academicuserRepository extends JpaRepository<academicUser, Long> {

    Optional<academicUser> findByEmail(String email);

}
