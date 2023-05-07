package com.springboot.candidate.payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

	
	private Long id;
	
	@NotBlank
	private String firstName;

	@NotBlank
	@Size(min = 2 , message = "Last name must be more than 2 letters " )
	private String lastName;

	@Email(message = "Email must be in proper format")
	private String email;

	@Pattern(regexp="^\\d{10}$",message="Invalid Mobile Number")
	private String phoneNumber;
	
	
	
	
}
