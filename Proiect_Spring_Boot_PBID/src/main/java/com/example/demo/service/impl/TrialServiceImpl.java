package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Trial;
import com.example.demo.repository.TrialRepository;
import com.example.demo.service.TrialService;

@Service
public class TrialServiceImpl implements TrialService{

	private TrialRepository TrialRepository;
	
	public TrialServiceImpl(TrialRepository TrialRepository) {
		super();
		this.TrialRepository = TrialRepository;
	}

	@Override
	public List<Trial> getAllTrials() {
		return TrialRepository.findAll();
	}

	@Override
	public Trial saveTrial(Trial Trial) {
		return TrialRepository.save(Trial);
	}

	@Override
	public Trial getTrialById(Long idtrial) {
		return TrialRepository.findById(idtrial).get();
	}

	@Override
	public Trial updateTrial(Trial Trial) {
		return TrialRepository.save(Trial);
	}

	@Override
	public void deleteTrialById(Long idtrial) {
		TrialRepository.deleteById(idtrial);	
	}
}
