package io.github.kri2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.kri2.dataaccess.UserDao;
import io.github.kri2.dataaccess.UserLogin;

@Controller
public class WebController {
	@Autowired
	UserDao userDao;
	String nameGlobal;
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String displayForm(Model model){
		model.addAttribute("userForm", new UserLogin());
		return "welcome";
	}
	/*this was for login, when spring security hadn't been set up yet*/
	@RequestMapping(value="/start", method=RequestMethod.POST)
	public String processForm(@ModelAttribute("userForm") UserLogin userLogin, 
								Model model){
		System.out.println(userLogin.getName());
		nameGlobal = userLogin.getName();
		String result="";
		if( userDao.findByName(userLogin.getName()) != null){
			//result="Welcome back "+userLogin.getName();//when on the same page
			model.addAttribute("name", userLogin.getName());
			return "redirect:/portfolio";
		}
		else{
			result="sorry no such user found";
			model.addAttribute("result", result);
			return "welcome";
		}
	}
	@RequestMapping("/welcome")
	public String serveWelcome(Model model){
		return "welcome";
	}
	@RequestMapping("/")
	public String serveIndex(Model model){
		return "index";
	}	
	
}
