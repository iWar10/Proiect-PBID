package com.example.demo.entity;

import java.sql.Date; 
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "trials")
public class Trial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtrial;

	@Column(name = "caseName", nullable = false)
	private String caseName;

	@Column(name = "caseType")
	private String caseType;

	@Column(name = "trialdate")
	private Date trialdate; 
	
	@OneToMany(mappedBy="trial", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Verdict> verdicts;

	public Trial() {

	}

	public Trial(String caseName, String caseType, Date trialdate, Set<Verdict> v) { 
		super();
		this.caseName = caseName;
		this.caseType = caseType;
		this.trialdate = trialdate;
		this.verdicts = v;
	}

	public Long getIdtrial() {
		return idtrial;
	}

	public void setIdtrial(Long idtrial) {
		this.idtrial = idtrial;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public Date getTrialdate() { 
		return trialdate;
	}

	public void setTrialdate(Date trialdate) { 
		this.trialdate = trialdate;
	}
	
	public Set<Verdict> getVerdicts() {
		return this.verdicts;
	}

	public void setVerdicts(Set<Verdict> verdicts) {
		this.verdicts = verdicts;
	}
}
