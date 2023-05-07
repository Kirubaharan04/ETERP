package com.springboot.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.candidate.entity.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

}
