package com.The_Inevitables.NavUP.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CRSName")
public class CRSName implements SuperEntity 
{
	public CRSName() {
		super();
	}
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "crsNameId", unique = true, nullable = false, updatable = false)
	private int crsNameId;
	
	@Column(name = "name")
	private String name;

	
	public int getCrsNameId() {
		return crsNameId;
	}

	public void setCrsNameId(int crsNameId) {
		this.crsNameId = crsNameId;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
