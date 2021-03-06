package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

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
public class RatingController {
	// TODO: Inject Rating service
	private static final Logger logger = LogManager.getLogger(RatingController.class);
	@Autowired
	RatingService ratingServ;

	@RequestMapping("/rating/list")
	public String home(Model model) {
		// TODO: find all Rating, add to model
		logger.info("affichage /rating/list = "+ new Date().toString());
		List<Rating> rating = ratingServ.getRating().findAll();
		model.addAttribute("rating", rating);
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		logger.info("affichage /rating/add = "+ new Date().toString());
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Rating list
		if (result.hasErrors()) {
			return "/rating/add";
		}
		logger.info("affichage /rating/validate = "+ new Date().toString());
		ratingServ.getRating().save(rating);
		List<Rating> ratingList = ratingServ.getRating().findAll();
		model.addAttribute("rating", ratingList);

		return "redirect:/rating/list";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get Rating by Id and to model then show to the form
		logger.info("affichage /rating/update/{id} = "+ new Date().toString());
		Rating rating = ratingServ.getRating().getOne(id);
		model.addAttribute("rating", rating);

		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		// TODO: check required fields, if valid call service to update Rating and
		// return Rating list
		/*if (result.hasErrors()) {
			return "/rating/update/{id}";
		}
		ratingServ.getRating().save(rating);
		List<Rating> ratingList = ratingServ.getRating().findAll();
		model.addAttribute("rating", ratingList);*/
		if (result.hasErrors()) {
			return "rating/update/{id}";
		}
		logger.info("affichage /rating/update/{id} = "+ new Date().toString());
		
		Rating ratingCurrent = ratingServ.getRating().getOne(id);
		
		ratingCurrent = rating;
		ratingCurrent.setId(id);
		
		ratingServ.getRating().save(ratingCurrent);
		List<Rating> ratingList = ratingServ.getRating().findAll();
		model.addAttribute("rating", ratingList);
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Rating by Id and delete the Rating, return to Rating list
		logger.info("affichage /rating/delete/{id} = "+ new Date().toString());
		Rating ratingCurrent = ratingServ.getRating().getOne(id);
		ratingServ.getRating().delete(ratingCurrent);
		List<Rating> ratingList = ratingServ.getRating().findAll();
		model.addAttribute("rating", ratingList);

		return "redirect:/rating/list";
	}
}
