package com.springboot.candidate.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.candidate.entity.CandidateEntity;
import com.springboot.candidate.exception.ResourceNotFoundException;
import com.springboot.candidate.payload.CandidateDto;
import com.springboot.candidate.payload.CandidateResponse;
import com.springboot.candidate.repository.CandidateRepository;
import com.springboot.candidate.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

	private CandidateRepository cand_Repository;
	@Autowired
	private ModelMapper mapper;

	public CandidateServiceImpl(CandidateRepository cand_Repository ) {
		super();
		this.cand_Repository = cand_Repository;
	
	}

	@Override
	public CandidateDto createEmployee(CandidateDto candidate_Dto) {
		CandidateEntity entity = maptoEntity(candidate_Dto);
		CandidateEntity newEmp = cand_Repository.save(entity);
		CandidateDto empResponse = maptoDto(newEmp);
		return empResponse;
	}

	public CandidateResponse getAllCandidate(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<CandidateEntity> candidate_Page = cand_Repository.findAll(pageable);
		List<CandidateEntity> candidate_List = candidate_Page.getContent();
		List<CandidateDto> content = candidate_List.stream().map(cand -> maptoDto(cand)).collect(Collectors.toList());
		CandidateResponse candidate_Response = CandidateResponse.build(content, candidate_Page.getNumber(),
				candidate_Page.getSize(), candidate_Page.getTotalElements(), candidate_Page.getTotalPages(),
				candidate_Page.isLast());
		return candidate_Response;
	}

	public CandidateDto getEmployeeById(long candidate_Id) {
		CandidateEntity entity = cand_Repository.findById(candidate_Id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", candidate_Id));
		return maptoDto(entity);
	}

	public CandidateDto updateEmployee(CandidateDto candidate_Dto, long candidate_Id) {
		CandidateEntity entity = cand_Repository.findById(candidate_Id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", candidate_Id));
		entity.setFirstName(candidate_Dto.getFirstName());
		entity.setLastName(candidate_Dto.getLastName());
		entity.setEmail(candidate_Dto.getEmail());
		entity.setPhoneNumber(candidate_Dto.getPhoneNumber());
		CandidateEntity newEmp = cand_Repository.save(entity);

		return maptoDto(newEmp);
	}

	public void deleteEmployee(long candidate_Id) {
		CandidateEntity entity = cand_Repository.findById(candidate_Id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", candidate_Id));
		cand_Repository.delete(entity);
	}

	private CandidateEntity maptoEntity(CandidateDto candidateDto) {
		/*
		 * CandidateEntity entity = new CandidateEntity();
		 * 
		 * entity.setFirstName(candidateDto.getFirstName());
		 * entity.setLastName(candidateDto.getLastName());
		 * entity.setEmail(candidateDto.getEmail());
		 * entity.setPhoneNumber(candidateDto.getPhoneNumber());
		 */
		CandidateEntity entity = mapper.map(candidateDto, CandidateEntity.class);
		return entity;
	}

	private CandidateDto maptoDto(CandidateEntity entity) {
		/*
		 * CandidateDto candidateResponse = new CandidateDto();
		 * 
		 * candidateResponse.setId(entity.getId());
		 * candidateResponse.setFirstName(entity.getFirstName());
		 * candidateResponse.setLastName(entity.getLastName());
		 * candidateResponse.setEmail(entity.getEmail());
		 * candidateResponse.setPhoneNumber(entity.getPhoneNumber());
		 */
		CandidateDto candidateDto = mapper.map(entity, CandidateDto.class);
		return candidateDto;
	}

}
