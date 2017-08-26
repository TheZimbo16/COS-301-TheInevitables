package com.epiuse.tenderplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "problem_statement")
public class ProblemStatement implements SuperEntity {

	@ManyToOne
	@JoinTable(name = "status")
	private ProblemStatementStatus status;

	@Column(name = "description")
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	private Long id;

	public ProblemStatement() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProblemStatementStatus getStatus() {
		return status;
	}

	public void setStatus(ProblemStatementStatus status) {
		this.status = status;
	}

}
