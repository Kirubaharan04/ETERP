package com.springboot.candidate.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.candidate.entity.Application;
import com.springboot.candidate.entity.CandidateEntity;
import com.springboot.candidate.exception.CandidateAPIException;
import com.springboot.candidate.exception.ResourceNotFoundException;
import com.springboot.candidate.payload.ApplicationDto;
import com.springboot.candidate.repository.ApplicationRepository;
import com.springboot.candidate.repository.CandidateRepository;
import com.springboot.candidate.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	private ApplicationRepository applicationRepository;
	private CandidateRepository candidateRepository;
	
	@Autowired
	private ModelMapper mapper;

	public ApplicationServiceImpl(ApplicationRepository applicationRepository,
			CandidateRepository candidateRepository) {
		super();
		this.applicationRepository = applicationRepository;
		this.candidateRepository = candidateRepository;

	}

	@Override
	public ApplicationDto createApplication(long candidateId, ApplicationDto applicationDto) {

		Application application = maptoEntity(applicationDto);

		// retrieve Candidate entity by id
		CandidateEntity candidate = candidateRepository.findById(candidateId)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", candidateId));

		// set candidate to application entity
		application.setCandidate(candidate);

		// Application entity to DB
		Application newApplication = applicationRepository.save(application);
		return maptoDto(newApplication);
	}

	// get all applications by candidate id

	@Override
	public List<ApplicationDto> getApplicationsByCandidateId(long candidateId) {
		System.out.println("Before fetch");
		List<Application> applications = findByCandidateId(candidateId);
		System.out.println("After fetch");
		// convert list of application entity to application dto
		return applications.stream().map(application -> maptoDto(application)).collect(Collectors.toList());
	}

	// get application by candidate id and application id
	public ApplicationDto getApplicationById(long candidateId, long applicationId) {
		// retrieve candidate by candidate id
		CandidateEntity candidate = candidateRepository.findById(candidateId)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", candidateId));
		// retrieve application by application id
		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Application", "id", applicationId));
		// checking Application belongs to valid
		if (!application.getCandidate().getId().equals(candidate.getId())) {
			throw new CandidateAPIException(HttpStatus.BAD_REQUEST, "Application does not belong to candidate");
		}

		return maptoDto(application);
	}

	// update application by application and candidate id

	@Override
	public ApplicationDto updateApplicationById(long applicationId, long candidateId, ApplicationDto applicationDto) {
		// retrieving candidate by candidate id
		CandidateEntity candidate = candidateRepository.findById(candidateId)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", candidateId));
		// retrieving application by application id
		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Application", "id", applicationId));
		if (!application.getCandidate().getId().equals(candidate.getId())) {
			throw new CandidateAPIException("Application does not belong to candidate", HttpStatus.BAD_REQUEST, "");
		}

		application.setJob_id(applicationDto.getJob_id());
		application.setStatus(applicationDto.getStatus());
		Application newApplication = applicationRepository.save(application);
		return maptoDto(newApplication);
	}

	public void deleteApplicationById(long candidateId, long applicationId) {

		// retrieving candidate by id
		CandidateEntity candidate = candidateRepository.findById(candidateId)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "id", candidateId));

		// retrieving application by id
		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new ResourceNotFoundException("application", "id", applicationId));

		if (!application.getCandidate().getId().equals(candidate.getId())) {
			throw new CandidateAPIException(HttpStatus.BAD_REQUEST, "Application doesn't belong to candidate");
		}
		applicationRepository.delete(application);

	}

	// Converting Application entity to Application Dto object

	private ApplicationDto maptoDto(Application application) {
		ApplicationDto applicationDto = mapper.map(application, ApplicationDto.class);
		return applicationDto;
	}

	// Converting Application Dto object to Application entity

	private Application maptoEntity(ApplicationDto applicationDto) {
		Application application = mapper.map(applicationDto, Application.class);
		return application;
	}

	public List<Application> findByCandidateId(long candidateId) {
		List<Application> collect = applicationRepository.findAll().stream()
				.filter(x -> x.getCandidate().getId() == candidateId).collect(Collectors.toList());
		return collect;
	}

}
