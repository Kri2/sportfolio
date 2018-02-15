package io.github.kri2.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.kri2.dataaccess.UserDao;
import io.github.kri2.dataaccess.UserLogin;

/**
 * The "Home" Controller
 * @author kriz
 *
 */
@Controller
public class WebController {
	
	final static org.slf4j.Logger mojlog = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	UserDao userDao;
	//@Autowired
	//Environment env;
	@Value("${welcome.sekret}")
	String sekretString;
	/**
	 * Testing logging library
	 * @param model
	 * @return viewname
	 */
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String displayForm(Model model){
		mojlog.info("WELCOME TO MY APP by kri2!");
		System.out.println("log should be sent now...");
		
		//String sekretString = env.getProperty("sekret");
		System.out.println(sekretString);
		mojlog.error("testowy error");
		
		model.addAttribute("userForm", new UserLogin());
		return "welcome";
	}

	
	
	@RequestMapping("/")
	public String serveIndex(Model model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authName = auth.getName();
		model.addAttribute("whoIsLoggedIn", authName);
		return "index";
	}	
}
