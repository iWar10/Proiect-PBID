package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Trial;

public interface TrialService {
	List<Trial> getAllTrials();
	
	Trial saveTrial(Trial trial);
	
	Trial getTrialById(Long idtrial);
	
	Trial updateTrial(Trial trial);
	
	void deleteTrialById(Long idtrial);
}
