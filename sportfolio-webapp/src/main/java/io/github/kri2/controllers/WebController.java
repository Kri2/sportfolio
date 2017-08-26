package io.github.kri2.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

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
	/**
	 * Here i will try to create pdf document
	 * @return
	 */
	@RequestMapping("/jasper")
	public String doJasper(){
		String userDir = System.getProperty("user.dir");
		String homeDir = System.getProperty("user.home");
		System.out.println(userDir);
		System.out.println(homeDir);
		//System.out.println(new File(".").getAbsolutePath());
		//URL url = getClass().getResource("/Users/kriz/Desktop/jasper_report_template.jrxml");
		
		try {
			File file = new ClassPathResource("jasper_report_template.jrxml").getFile();
			InputStream inputStream = new FileInputStream(file);//getClass().getResourceAsStream(file);
			System.out.println(inputStream);
			try {
				JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
				//This is not necessary
				JRSaver.saveObject(jasperReport, "/Users/kriz/Desktop/jasper_report_template.jasper");
				List<User> users = (List<User>)userDao.findAll();
				
				JRBeanCollectionDataSource usersDS = new JRBeanCollectionDataSource(users);
				
				JRPdfExporter exporter = new JRPdfExporter();
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, usersDS);
				
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("/Users/kriz/Desktop/employeeReport.pdf"));
				SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
				reportConfig.setSizePageToContent(true);
				reportConfig.setForceLineBreakPolicy(false);
				
				SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
				exportConfig.setMetadataAuthor("Krzysztof Turlej");
				exportConfig.setEncrypted(true);
				exportConfig.setAllowedPermissionsHint("PRINTING");
				
				exporter.setConfiguration(reportConfig);
				exporter.setConfiguration(exportConfig);
				
				exporter.exportReport();

			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//InputStream inputStream = getClass().getResourceAsStream("classpath:jasper_report_template.jrxml");
		
		
		
		//InputStream inputStream = getClass().getResourceAsStream("/Users/kriz/Desktop/jasper_report_template.jrxml");
		
		
		
		return "redirect:/";
	}
	@RequestMapping("/")
	public String serveIndex(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authName = auth.getName();
		model.addAttribute("whoIsLoggedIn", authName);
		return "index";
	}	
}
