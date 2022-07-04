package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repositories.RatingRepository;
@Service
public class RatingService {
	@Autowired
	RatingRepository ratingRepo;
	
	public RatingRepository getRating() {
		
		return ratingRepo;
	}

}
