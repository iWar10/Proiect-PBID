package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "verdicts")
public class Verdict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idverdict;
    
    @ManyToOne
    @JoinColumn(name = "idjudge", nullable = false)
    private Judge judge;
    
    @ManyToOne
    @JoinColumn(name = "idtrial", nullable = false)
    private Trial trial;

    @Column(name = "decision", nullable = false)
    private String decision;

    @Column(name = "reasoning")
    private String reasoning;

    @Column(name = "verdictdate")
    private Date verdictdate;

    public Verdict() {

    }

    public Verdict(Judge judge, Trial trial, String decision, String reasoning, Date verdictdate) {
        super();
        this.judge = judge;
        this.trial = trial;
        this.decision = decision;
        this.reasoning = reasoning;
        this.verdictdate = verdictdate;
    }

    public Long getIdverdict() {
        return this.idverdict;
    }

    public void setIdverdict(Long idverdict) {
        this.idverdict = idverdict;
    }

    public Judge getJudge() {
        return this.judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public Trial getTrial() {
        return this.trial;
    }

    public void setTrial(Trial trial) {
        this.trial = trial;
    }

    public String getDecision() {
        return this.decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getReasoning() {
        return this.reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }

    public Date getVerdictdate() {
        return this.verdictdate;
    }

    public void setVerdictdate(Date verdictdate) {
        this.verdictdate = verdictdate;
    }
}
