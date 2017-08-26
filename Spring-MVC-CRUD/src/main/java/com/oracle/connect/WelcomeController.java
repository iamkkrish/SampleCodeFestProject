package com.oracle.connect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	@RequestMapping("/welcome")
	public ModelAndView helloMethod() {
		String message = "Welcome to SPRING MVC !!! This is Welcome Method";
		return new ModelAndView("welcomepage", "welcomeMethodMessage", message);
	}
}
