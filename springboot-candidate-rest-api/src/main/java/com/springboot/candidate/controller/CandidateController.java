package com.springboot.candidate.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.candidate.payload.CandidateDto;
import com.springboot.candidate.payload.CandidateResponse;
import com.springboot.candidate.service.CandidateService;
import com.springboot.candidate.utils.AppConstants;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

	private static Logger logger = LogManager.getLogger(CandidateController.class);
	

	private CandidateService candidate_Service;

	public CandidateController(CandidateService candidate_Service) {
		super();
		this.candidate_Service = candidate_Service;
	}
	
	@PostMapping("/add")
	public ResponseEntity<CandidateDto> addEmployee(@Valid @RequestBody CandidateDto candidate_Dto) {
		
		CandidateDto createEmployee = candidate_Service.createEmployee(candidate_Dto);
		return new ResponseEntity<CandidateDto>(createEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/getall")
	public ResponseEntity<CandidateResponse> getAllEmployee(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		logger.info("Logging for get all candidate method");
		return ResponseEntity.ok(candidate_Service.getAllCandidate(pageNo, pageSize, sortBy, sortDir));
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<CandidateDto> getEmployeeById(@PathVariable("id") long candidate_id) {
		return ResponseEntity.ok(candidate_Service.getEmployeeById(candidate_id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CandidateDto> updateEmployeeById(@Valid @RequestBody CandidateDto candidate_Dto,
			@PathVariable("id") long candidate_id) {
		return ResponseEntity.ok(candidate_Service.updateEmployee(candidate_Dto, candidate_id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long candidate_id) {
		candidate_Service.deleteEmployee(candidate_id);
		return new ResponseEntity<>("Candidate Deleted Successfully", HttpStatus.OK);
	}
}
