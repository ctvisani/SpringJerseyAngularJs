package com.concretepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info1")
public class HomeController {

	@RequestMapping("/home")
	public String home() {
		return "home";
	}
}
