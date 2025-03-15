package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Judge;
import com.example.demo.repository.JudgeRepository;
import com.example.demo.service.JudgeService;

@Service
public class JudgeServiceImpl implements JudgeService{

	private JudgeRepository JudgeRepository;
	
	public JudgeServiceImpl(JudgeRepository JudgeRepository) {
		super();
		this.JudgeRepository = JudgeRepository;
	}

	@Override
	public List<Judge> getAllJudges() {
		return JudgeRepository.findAll();
	}

	@Override
	public Judge saveJudge(Judge Judge) {
		return JudgeRepository.save(Judge);
	}

	@Override
	public Judge getJudgeById(Long idjudge) {
		return JudgeRepository.findById(idjudge).get();
	}

	@Override
	public Judge updateJudge(Judge Judge) {
		return JudgeRepository.save(Judge);
	}

	@Override
	public void deleteJudgeById(Long idjudge) {
		JudgeRepository.deleteById(idjudge);	
	}
}
