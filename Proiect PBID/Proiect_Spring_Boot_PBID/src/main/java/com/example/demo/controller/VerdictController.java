package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Verdict;
import com.example.demo.service.VerdictService;

import com.example.demo.entity.Trial;
import com.example.demo.repository.JudgeRepository;
import com.example.demo.repository.TrialRepository;
import com.example.demo.entity.Judge;

@Controller
public class VerdictController {
	
	private VerdictService verdictService;
	
	@Autowired
	private TrialRepository trialRepo;
	
	@Autowired
	private JudgeRepository judgeRepo;

	public VerdictController(VerdictService verdictService) {
		super();
		this.verdictService = verdictService;
	}
	
	@GetMapping("/verdicts")
	public String listVerdicts(Model model) {
		model.addAttribute("verdicts", verdictService.getAllVerdicts());
		return "verdicts";
	}
	
	@GetMapping("/verdicts/new")
	public String createVerdictForm(Model model) {
		List<Trial> allTrials = trialRepo.findAll();
		List<Judge> allJudges = judgeRepo.findAll();
		
		Verdict verdict = new Verdict();
		model.addAttribute("verdict", verdict);
		
		model.addAttribute("allTrials", allTrials);
		model.addAttribute("allJudges", allJudges);
		
		return "create_verdict";
		
	}
	
	@PostMapping("/verdicts")
	public String saveVerdict(@ModelAttribute("verdict") Verdict verdict) {		
		verdictService.saveVerdict(verdict);
		return "redirect:/verdicts";
	}
	
	@GetMapping("/verdicts/edit/{idverdict}")
	public String editVerdictForm(@PathVariable Long idverdict, Model model) {
		List<Trial> allTrials = trialRepo.findAll();
		List<Judge> allJudges = judgeRepo.findAll();

		model.addAttribute("verdict", verdictService.getVerdictById(idverdict));
		model.addAttribute("allTrials", allTrials);
		model.addAttribute("allJudges", allJudges);
		
		return "edit_verdict";
	}

	@PostMapping("/verdicts/{idverdict}")
	public String updateVerdict(@PathVariable Long idverdict,
			@ModelAttribute("verdict") Verdict verdict,
			Model model) {
		
		Verdict existingVerdict = verdictService.getVerdictById(idverdict);
		existingVerdict.setIdverdict(idverdict);
		existingVerdict.setTrial(verdict.getTrial());
		existingVerdict.setJudge(verdict.getJudge());
		existingVerdict.setVerdictdate(verdict.getVerdictdate());
		existingVerdict.setDecision(verdict.getDecision());
		existingVerdict.setReasoning(verdict.getReasoning());

		verdictService.updateVerdict(existingVerdict);
		return "redirect:/verdicts";		
	}
	
	
	@GetMapping("/verdicts/{idverdict}")
	public String deleteVerdict(@PathVariable Long idverdict) {
		verdictService.deleteVerdictById(idverdict);
		return "redirect:/verdicts";
	}	
}
