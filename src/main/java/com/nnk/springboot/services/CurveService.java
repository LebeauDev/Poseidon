package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repositories.CurvePointRepository;
@Service
public class CurveService {
	
	@Autowired
	CurvePointRepository curveRepo;
	
	public CurvePointRepository getCurveRepo() {
		
		return curveRepo;
	}

}
