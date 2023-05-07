package com.springboot.candidate.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
	
	private long applicationId;
	
	@NotNull
	private int job_id;
	
	@NotBlank(message = "Status must not be blank")
	private String status;
	


}
