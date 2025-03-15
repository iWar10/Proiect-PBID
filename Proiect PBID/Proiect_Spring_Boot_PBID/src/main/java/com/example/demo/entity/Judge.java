package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "judges")
public class Judge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idjudge;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "court")
	private String court;
	
	@OneToMany(mappedBy="judge", cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Verdict> verdicts;

	public Judge() {

	}

	public Judge(String lastname, String firstname, String court, Set<Verdict> v) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.court = court;
		this.verdicts = v;
	}

	public Long getIdjudge() {
		return idjudge;
	}

	public void setIdjudge(Long idjudge) {
		this.idjudge = idjudge;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}
	
	public Set<Verdict> getVerdicts() {
		return this.verdicts;
	}

	public void setVerdicts(Set<Verdict> verdicts) {
		this.verdicts = verdicts;
	}
}
