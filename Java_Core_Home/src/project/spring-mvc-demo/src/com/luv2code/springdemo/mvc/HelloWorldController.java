package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}

	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}

	// new method to read form data and add data to the model
	@RequestMapping("/processFormVersionTwo")
	public String letShoutDude(HttpServletRequest request, Model model) {
		String theName = "Yo! " + request.getParameter("studentName").toUpperCase();
		model.addAttribute("message", theName);
		return "helloworld";
	}
	
	@RequestMapping("/processFormVersionThree")
	public String letShoutDudeYo(@RequestParam("studentName") String theName, Model model) {
		theName = "Hey, My Friend " + theName.toUpperCase();
		model.addAttribute("message", theName);
		return "helloworld";
	}
	
}
