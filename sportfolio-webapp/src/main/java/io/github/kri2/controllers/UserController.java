package io.github.kri2.controllers;

import org.slf4j.LoggerFactory;
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
/**
 * The UserController groups mapped methods responsible for user operations,
 * like adding new user, loging in the user, etc. 
 * TODO: login controller should probably be separated into its own class
 * @author kriz
 *
 */
@Controller
public class UserController {
	final static org.slf4j.Logger userlog = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserDao userDao;
	@RequestMapping(value="login")
	public String displayLoginForm(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("whoIsLoggedIn", auth.getName());
		return "login";
	}
	// adduser start
	@RequestMapping(value="adduser",method=RequestMethod.GET)
	public String addUserForm(Model model){
		userlog.error("New user is being created.");
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
