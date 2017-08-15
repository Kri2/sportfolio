package io.github.kri2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.kri2.dataaccess.UserDao;
import io.github.kri2.dataaccess.UserLogin;
import io.github.kri2.domain.User;

@Controller
public class UserController {
	@Autowired
	UserDao userDao;
	@RequestMapping(value="login")
	public String displayLoginForm(){
		return "login";
	}
	// adduser start
	@RequestMapping(value="adduser",method=RequestMethod.GET)
	public String addUserForm(Model model){
		model.addAttribute("userCredentials", new UserLogin());
		//shows who is currently logged in
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authName = auth.getName();
		model.addAttribute("whoIsLoggedIn", authName);
		return "adduser";
	}
	@RequestMapping(value="adduser",method=RequestMethod.POST)
	public String addUserFormHandle(@ModelAttribute("userCredentials") UserLogin userLogin){
		User user = new User();
		user.setName(userLogin.getName());
		user.setPassword(userLogin.getPassword());
		user.setEnabled(true);
		userDao.save(user);
		return "redirect:/portfolio";
	}
	//adduser end
}
