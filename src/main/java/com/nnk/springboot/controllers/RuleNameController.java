package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

@Controller
public class RuleNameController {
	// TODO: Inject RuleName service
	private static final Logger logger = LogManager.getLogger(RuleNameController.class);
	@Autowired
	RuleNameService ruleNameServ;

	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		// TODO: find all RuleName, add to model
		logger.info("affichage /ruleName/list = " + new Date().toString());
		List<RuleName> ruleNameList = ruleNameServ.getRuleName().findAll();
		model.addAttribute("ruleName", ruleNameList);

		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		logger.info("affichage /ruleName/add = " + new Date().toString());
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return RuleName list
		if (result.hasErrors()) {
			return "ruleName/add";
		}
		logger.info("affichage /ruleName/validate = " + new Date().toString());
		ruleNameServ.getRuleName().save(ruleName);
		List<RuleName> ruleNameList = ruleNameServ.getRuleName().findAll();
		model.addAttribute("ruleName", ruleNameList);
		return "redirect:/ruleName/List";

	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get RuleName by Id and to model then show to the form
		logger.info("affichage /ruleName/update/{id} = " + new Date().toString());
		RuleName ruleNameCurrent = ruleNameServ.getRuleName().getOne(id);
		model.addAttribute("ruleName", ruleNameCurrent);
		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		// TODO: check required fields, if valid call service to update RuleName and
		// return RuleName list
		if (result.hasErrors()) {
			return "/ruleName/update/{id}";
		}
		logger.info("affichage /ruleName/update/{id} = " + new Date().toString());
		
		RuleName ruleNameCurrent = ruleNameServ.getRuleName().getOne(id);

		ruleNameCurrent = ruleName;
		ruleNameCurrent.setId(id);
		ruleNameServ.getRuleName().save(ruleName);
		List<RuleName> ruleNameList = ruleNameServ.getRuleName().findAll();
		model.addAttribute("ruleName", ruleNameList);
		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		// TODO: Find RuleName by Id and delete the RuleName, return to Rule list
		logger.info("affichage ruleName/delete/{id} = " + new Date().toString());
		RuleName ruleNameCurrent = ruleNameServ.getRuleName().getOne(id);
		ruleNameServ.getRuleName().delete(ruleNameCurrent);
		List<RuleName> ruleNameList = ruleNameServ.getRuleName().findAll();
		model.addAttribute("ruleName", ruleNameList);
		return "redirect:/ruleName/list";
	}
}
