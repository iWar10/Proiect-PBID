package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Judge;
import com.example.demo.service.JudgeService;

@Controller
public class JudgeController {
	
	private JudgeService judgeService;

	public JudgeController(JudgeService judgeService) {
		super();
		this.judgeService = judgeService;
	}
	
	@GetMapping("/judges")
	public String listJudges(Model model) {
		model.addAttribute("judges", judgeService.getAllJudges());
		return "judges";
	}
	
	@GetMapping("/judges/new")
	public String createJudgeForm(Model model) {
		
		Judge judge = new Judge();
		model.addAttribute("judge", judge);
		return "create_judge";
		
	}
	
	@PostMapping("/judges")
	public String saveJudge(@ModelAttribute("judge") Judge judge) {
		judgeService.saveJudge(judge);
		return "redirect:/judges";
	}
	
	@GetMapping("/judges/edit/{idjudge}")
	public String editJudgeForm(@PathVariable Long idjudge, Model model) {
		model.addAttribute("judge", judgeService.getJudgeById(idjudge));
		return "edit_judge";
	}

	@PostMapping("/judges/{idjudge}")
	public String updateJudge(@PathVariable Long idjudge,
			@ModelAttribute("judge") Judge judge,
			Model model) {
		
		Judge existingJudge = judgeService.getJudgeById(idjudge);
		existingJudge.setIdjudge(idjudge);
		existingJudge.setLastname(judge.getLastname());
		existingJudge.setFirstname(judge.getFirstname());
		existingJudge.setCourt(judge.getCourt());
		
		judgeService.updateJudge(existingJudge);
		return "redirect:/judges";		
	}
	
	
	@GetMapping("/judges/{idjudge}")
	public String deleteJudge(@PathVariable Long idjudge) {
		judgeService.deleteJudgeById(idjudge);
		return "redirect:/judges";
	}	
}
