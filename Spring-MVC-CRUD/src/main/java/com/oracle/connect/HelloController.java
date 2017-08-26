package com.oracle.connect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public ModelAndView helloMethod(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		if (password.equals("admin") || userName.equals("admin")) {
			String message = "Hello Mr. " + userName;
			return new ModelAndView("hellopage", "helloMethodMessage", message);
		} else {
			return new ModelAndView("errorpage", "errorMessage", "Sorry, "+ userName +" Invalid Credentials !!");
		}

	}
}