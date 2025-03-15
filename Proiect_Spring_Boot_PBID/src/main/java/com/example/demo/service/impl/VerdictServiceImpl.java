package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Verdict;
import com.example.demo.repository.VerdictRepository;
import com.example.demo.service.VerdictService;

@Service
public class VerdictServiceImpl implements VerdictService{

	private VerdictRepository verdictRepository;
	
	public VerdictServiceImpl(VerdictRepository verdictRepository) {
		super();
		this.verdictRepository = verdictRepository;
	}

	@Override
	public List<Verdict> getAllVerdicts() {
		return verdictRepository.findAll();
	}

	@Override
	public Verdict saveVerdict(Verdict verdict) {
		return verdictRepository.save(verdict);
	}

	@Override
	public Verdict getVerdictById(Long idverdict) {
		return verdictRepository.findById(idverdict).get();
	}

	@Override
	public Verdict updateVerdict(Verdict verdict) {
		return verdictRepository.save(verdict);
	}

	@Override
	public void deleteVerdictById(Long idverdict) {
		verdictRepository.deleteById(idverdict);	
	}
}
