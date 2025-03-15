package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Verdict;

public interface VerdictService {
	List<Verdict> getAllVerdicts();
	
	Verdict saveVerdict(Verdict verdict);
	
	Verdict getVerdictById(Long idverdict);
	
	Verdict updateVerdict(Verdict verdict);
	
	void deleteVerdictById(Long idverdict);
}
