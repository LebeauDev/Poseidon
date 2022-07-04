package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repositories.RuleNameRepository;
@Service
public class RuleNameService {
	
	@Autowired
	RuleNameRepository ruleNameRepo;
	
	public RuleNameRepository getRuleName() {
		return ruleNameRepo;
	}

}
