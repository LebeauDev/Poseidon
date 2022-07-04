package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repositories.TradeRepository;
@Service
public class TradeService {
	@Autowired
	TradeRepository tardeRepo;

	public TradeRepository getTrade() {

		return tardeRepo;
	}

}
