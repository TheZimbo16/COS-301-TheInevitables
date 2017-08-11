package com.The_Inevitables.NavUP.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "problem_statement_status")
public class ProblemStatementStatus implements SuperEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	private Long id;

	private Status value;

	public enum Status {
		CREATED
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getValue() {
		return value;
	}

	public void setValue(Status value) {
		this.value = value;
	}

}
