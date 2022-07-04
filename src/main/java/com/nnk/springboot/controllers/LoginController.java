package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;


import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app")
public class LoginController {
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("login")
    public ModelAndView login() {
    	
    	logger.info("affichage login = "+ new Date().toString());
    	
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
    	logger.info("affichage secure/article-details = "+ new Date().toString());
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
    	logger.error("error"+ new Date().toString());
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        Authentication authentication = SecurityContextHolder.getContext()
        	    .getAuthentication();
        System.out.println(authentication.getAuthorities().toString());
        mav.setViewName("403");
        return mav;
    }
}
