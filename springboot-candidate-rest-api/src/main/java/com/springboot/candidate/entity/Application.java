package com.springboot.candidate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "application")
public class Application {
	@Id
	/*
	 * @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	 * 
	 * @GenericGenerator(name = "sequence", parameters = @Parameter(name =
	 * "initial_value", value = "1000"), strategy = "increment")
	 */
	@GenericGenerator(name = "customers-sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "customers_sequence"),
			@Parameter(name = "initial_value", value = "1001"), @Parameter(name = "increment_size", value = "1") })
	@GeneratedValue(generator = "customers-sequence-generator")
	private long applicationId;
	@Column(nullable = false)
	private int job_id;
	@Column(nullable = false)
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidateId", referencedColumnName = "id", nullable = false)
	private CandidateEntity candidate;


}
