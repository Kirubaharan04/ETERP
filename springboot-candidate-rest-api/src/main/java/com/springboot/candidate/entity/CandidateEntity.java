package com.springboot.candidate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "candidate")
public class CandidateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
	@GenericGenerator(name = "seq", strategy = "increment")
	private Long id;

	@Column(name = "fName", nullable = false)
	private String firstName;
	
	@Column(name = "lName")
	private String lastName;
	
	
	@Column(nullable = false)
	private String email;
	

	@Column(name = "phone", nullable = false)
	private String phoneNumber;
	
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Application> application = new HashSet<Application>();
	
	

}
