package com.springboot.candidate.service;

import java.util.List;

import com.springboot.candidate.payload.ApplicationDto;

public interface ApplicationService {

	ApplicationDto createApplication(long candidateId , ApplicationDto applicationDto );
	
	List<ApplicationDto> getApplicationsByCandidateId(long candidateId);
	
	ApplicationDto getApplicationById(long candidateId , long applicationId);

	ApplicationDto updateApplicationById(long applicationId , long candidateId , ApplicationDto applicationDto);
	
	void deleteApplicationById(long candidateId , long applicationId); 
}
