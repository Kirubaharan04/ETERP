package com.springboot.candidate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.candidate.payload.ApplicationDto;
import com.springboot.candidate.service.ApplicationService;

@RestController
@RequestMapping("/api")
public class ApplicationController {

	private ApplicationService applicationService;

	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}


	@PostMapping("/candidate/{candidateId}/application")
	public ResponseEntity<ApplicationDto> createApplication(@PathVariable("candidateId") long candidateId,
			@Valid @RequestBody ApplicationDto applicationDto) {
		return new ResponseEntity<ApplicationDto>(applicationService.createApplication(candidateId, applicationDto),
				HttpStatus.CREATED);
	}

	@GetMapping("/candidate/{candidateId}/applications")
	public ResponseEntity<List<ApplicationDto>> getAllApplicationsByCandidateID(
			@PathVariable("candidateId") long candidateId) {
		return ResponseEntity.ok(applicationService.getApplicationsByCandidateId(candidateId));
	}

	@GetMapping("/candidate/{candidateId}/application/{applicationId}")
	public ResponseEntity<ApplicationDto> getApplicationById(@PathVariable("candidateId") long candidateId,
			@PathVariable("applicationId") long applicationId) {

		return new ResponseEntity<>(applicationService.getApplicationById(candidateId, applicationId), HttpStatus.OK);
	}


	@PutMapping("/candidate/{candidateId}/application/{applicationId}")
	public ResponseEntity<ApplicationDto> updateApplicationById(@PathVariable("applicationId") long applicationId,
			@PathVariable("candidateId") long candidateId,@Valid @RequestBody ApplicationDto applicationDto) {
		return ResponseEntity.ok(applicationService.updateApplicationById(applicationId, candidateId, applicationDto));
	}

	@DeleteMapping("/candidate/{candidateId}/application/{applicationId}")
	public ResponseEntity<String> deleteApplicationById(@PathVariable("candidateId") long candidateId,
			@PathVariable("applicationId") long applicationId) {
		applicationService.deleteApplicationById(candidateId, applicationId);
		return new ResponseEntity<>("Application deleted successfully", HttpStatus.OK);
	}
}
