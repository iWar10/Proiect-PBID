package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Trial;
import com.example.demo.service.TrialService;

@Controller
public class TrialController {
	
	private TrialService trialService;

	public TrialController(TrialService trialService) {
		super();
		this.trialService = trialService;
	}
	
	@GetMapping("/trials")
	public String listPacienti(Model model) {
		model.addAttribute("trials", trialService.getAllTrials());
		return "trials";
	}
	
	@GetMapping("/trials/new")
	public String createMedicForm(Model model) {
		
		Trial trial = new Trial();
		model.addAttribute("trial", trial);
		return "create_trial";
		
	}
	
	@PostMapping("/trials")
	public String saveMedic(@ModelAttribute("trial") Trial trial) {
		trialService.saveTrial(trial);
		return "redirect:/trials";
	}
	
	@GetMapping("/trials/edit/{idtrial}")
	public String editMedicForm(@PathVariable Long idtrial, Model model) {
		model.addAttribute("trial", trialService.getTrialById(idtrial));
		return "edit_trial";
	}

	@PostMapping("/trials/{idtrial}")
	public String updateMedic(@PathVariable Long idtrial,
			@ModelAttribute("trial") Trial trial,
			Model model) {
		
		Trial trialExistent = trialService.getTrialById(idtrial);
		trialExistent.setIdtrial(idtrial);
		trialExistent.setCaseName(trial.getCaseName());
		trialExistent.setCaseType(trial.getCaseType());
		trialExistent.setTrialdate(trial.getTrialdate());
		
		trialService.updateTrial(trialExistent);
		return "redirect:/trials";		
	}
	
	
	@GetMapping("/trials/{idtrial}")
	public String deleteMedic(@PathVariable Long idtrial) {
		trialService.deleteTrialById(idtrial);
		return "redirect:/trials";
	}	
}