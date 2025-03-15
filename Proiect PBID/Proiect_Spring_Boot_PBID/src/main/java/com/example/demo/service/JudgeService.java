package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Judge;

public interface JudgeService {
	List<Judge> getAllJudges();
	
	Judge saveJudge(Judge judge);
	
	Judge getJudgeById(Long idjudge);
	
	Judge updateJudge(Judge judge);
	
	void deleteJudgeById(Long idjudge);
}
