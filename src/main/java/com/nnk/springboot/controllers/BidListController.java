package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

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
public class BidListController {
	// TODO: Inject Bid service
	private static final Logger logger = LogManager.getLogger(BidListController.class);
	@Autowired
	BidListService bidListServ;

	@RequestMapping("/bidList/list")
	public String home(Model model) {
		// TODO: call service find all bids to show to the view
		List<BidList> listBid = bidListServ.getListBid().findAll();
		model.addAttribute("bidList", listBid);

		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		logger.info("affichage bidList/add = "+ new Date().toString());
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return bid list
		if (result.hasErrors()) {
			return "bidList/add";

		}
		logger.info("affichage bidList/validate = "+ new Date().toString());
		bid.setCreationDate(new Timestamp(System.currentTimeMillis()));
		bidListServ.getListBid().save(bid);
		List<BidList> listBid = bidListServ.getListBid().findAll();
		model.addAttribute("bidList", listBid);
		return "redirect:/bidList/list";

	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		// TODO: get Bid by Id and to model then show to the form
		logger.info("affichage /bidList/update/{id} = "+ new Date().toString());
		BidList bidListCurrent = bidListServ.getListBid().getOne(id);
		model.addAttribute("bidList", bidListCurrent);
		
		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		// TODO: check required fields, if valid call service to update Bid and return
		// list Bid
		if (result.hasErrors()) {
			return "bidList/update/{id}";
		}
		logger.info("affichage bidList/update/{id} = "+ new Date().toString());
		BidList bidListCurrent = bidListServ.getListBid().getOne(id);
		
		bidListCurrent = bidList;
		bidListCurrent.setBidListId(id);
		
		bidListServ.getListBid().save(bidListCurrent);
		List<BidList> listBid = bidListServ.getListBid().findAll();
		model.addAttribute("bidList", listBid);
		
		
		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		// TODO: Find Bid by Id and delete the bid, return to Bid list
		logger.info("affichage bidList/delete/{id} = "+ new Date().toString());
		BidList bidListCurrent = bidListServ.getListBid().getOne(id);
		bidListServ.getListBid().delete(bidListCurrent);
		List<BidList> bidList = bidListServ.getListBid().findAll();
		model.addAttribute("bidList", bidList);
		return "redirect:/bidList/list";
	}
}
