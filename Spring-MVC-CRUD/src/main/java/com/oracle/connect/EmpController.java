package com.oracle.connect;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpController {

	@Autowired
	EmpDao dao;

	@RequestMapping("/empform")
	public ModelAndView Showfrom() {
		return new ModelAndView("empform", "command", new Emp());
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("emp") Emp emp) {
		dao.createEmployee(emp);
		return new ModelAndView("redirect:/viewemp");
	}

	@RequestMapping("/viewemp")
	public ModelAndView viewEmp() {
		List<Emp> list = dao.getEmployees();
		return new ModelAndView("viewemp", "list", list);
	}

	@RequestMapping(value = "/editemp/{id}")
	public ModelAndView edit(@PathVariable int id) {
		Emp emp = dao.getEmployeeById(id);
		return new ModelAndView("empeditform", "command", emp);
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("emp") Emp emp) {
		dao.updateEmployee(emp);
		return new ModelAndView("redirect:/viewemp");

	}

	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		dao.deleteEmployee(id);
		return new ModelAndView("redirect:/viewemp");
	}
}
