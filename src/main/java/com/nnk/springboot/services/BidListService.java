package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repositories.BidListRepository;
@Service
public class BidListService {
	
	@Autowired
	BidListRepository bidListRepo;
	
	public BidListRepository getListBid () {
		
	
	return bidListRepo;
	
	}

}
