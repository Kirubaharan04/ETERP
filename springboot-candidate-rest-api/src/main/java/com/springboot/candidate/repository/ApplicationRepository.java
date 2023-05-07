package com.springboot.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.candidate.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	
	

}
