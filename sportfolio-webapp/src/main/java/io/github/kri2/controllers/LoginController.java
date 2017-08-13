package io.github.kri2.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.kri2.dto.UserDto;

@Controller
public class LoginController {
	@RequestMapping(value="login2", method=RequestMethod.GET)
	public String displayLogin(Model model){
		model.addAttribute("form", new UserDto());
		return "login2";
	}
	@RequestMapping(value="login2", method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("form") UserDto userDto, Model model){
		model.addAttribute("credentials",userDto);
		return "login2";
	}
	@RequestMapping(value="/login3", method = RequestMethod.GET)
	  public String printUser(ModelMap model) {

	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      System.out.println(name);
	      //model.addAttribute("username", name);
	      //inny sposob
	      
	      
	      return "login";

	  }
	
}
