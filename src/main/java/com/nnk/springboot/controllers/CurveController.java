package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurveService;

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

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

@Controller
public class CurveController {
	// TODO: Inject Curve Point service
	private static final Logger logger = LogManager.getLogger(CurveController.class);

	@Autowired
	CurveService curveServ;

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		// TODO: find all Curve Point, add to model
		logger.info("affichage /curvePoint/list = "+ new Date().toString());
		List<CurvePoint> curveList = curveServ.getCurveRepo().findAll();
		model.addAttribute("curvePoints", curveList);

		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		logger.info("affichage /curvePoint/add = "+ new Date().toString());
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Curve list
		if (result.hasErrors()) {
			return "curvePoint/add";
		}
		logger.info("affichage /curvePoint/validate = "+ new Date().toString());
		curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
		curveServ.getCurveRepo().save(curvePoint);
		List<CurvePoint> curveList = curveServ.getCurveRepo().findAll();
		model.addAttribute("curvePoint", curveList);

		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get CurvePoint by Id and to model then show to the form
		logger.info("affichage /curvePoint/update/{id} = "+ new Date().toString());
		CurvePoint curvePointCurrent = curveServ.getCurveRepo().getOne(id);
		model.addAttribute("curvePoint", curvePointCurrent);

		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		// TODO: check required fields, if valid call service to update Curve and return
		// Curve list
		/*if (result.hasErrors()) {
			return "/curvePoint/update/{id}";
		}
		curveServ.getCurveRepo().save(curvePoint);
		List<CurvePoint> curveList = curveServ.getCurveRepo().findAll();
		model.addAttribute("curvePoint", curveList);*/
		
		if (result.hasErrors()) {
			return "curvePoint/update/{id}";
		}
		logger.info("affichage /curvePoint/update/{id} = "+ new Date().toString());
		
		CurvePoint curvePointCurrent = curveServ.getCurveRepo().getOne(id);
		
		curvePointCurrent = curvePoint;
		curvePointCurrent.setCurveId(id);
		
		curveServ.getCurveRepo().save(curvePointCurrent);
		List<CurvePoint> curvePointList = curveServ.getCurveRepo().findAll();
		model.addAttribute("curvePoint", curvePointList);

		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Curve by Id and delete the Curve, return to Curve list
		logger.info("affichage /curvePoint/delete/{id} = "+ new Date().toString());
		CurvePoint curvePointCurrent = curveServ.getCurveRepo().getOne(id);
		curveServ.getCurveRepo().delete(curvePointCurrent);
		List<CurvePoint> curveList = curveServ.getCurveRepo().findAll();
		model.addAttribute("curvePoint", curveList);

		return "redirect:/curvePoint/list";
	}
}
