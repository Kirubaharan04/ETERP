package com.springboot.candidate.service;

import com.springboot.candidate.payload.CandidateDto;
import com.springboot.candidate.payload.CandidateResponse;

public interface CandidateService {
	CandidateDto createEmployee(CandidateDto candidateDto);
	public CandidateResponse getAllCandidate(int pageNo , int pageSize , String sortBy, String sortDir);
	public CandidateDto getEmployeeById(long candidate_Id);
	public CandidateDto updateEmployee(CandidateDto candidateDto , long candidate_Id);
	void deleteEmployee(long candidate_Id);
}
