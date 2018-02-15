package io.github.kri2.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.kri2.dataaccess.UserDao;
import io.github.kri2.domain.User;
import io.github.kri2.service.JasperFile;
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
public class JasperPdfController {
	/**
	 * Here i will try to create pdf document using jasper
	 * @return
	 */
	@Autowired
	UserDao userDao;
	@RequestMapping("/jasper")
	
	public String doJasper(){
		/*
		try {
			JasperReport subreport = (JasperReport)JRLoader.loadObjectFromFile("build/reports/ProductReport.jasper");
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		//File file = new File(classLoader.getResource(".").getFile() + "/test.xml");
		try {
			// STEP 1 is to have .jrxml file prepared (with mapped fields from bean or database), now need to have it as inputStream
			InputStream reportStream = JasperFile.getStream("jasper_report_template.jrxml");
			InputStream subReportStream = JasperFile.getStream("subreport_01.jrxml");
			
			System.out.println(reportStream);
			System.out.println(subReportStream);
			String fileLocation = new ClassPathResource(".").getFile().getAbsolutePath();//needed to have path to load file later
			System.out.println(fileLocation);
			//InputStream reportStream2 = new ClassPathResource("subreport_01.jrxml").getInputStream();
			//System.out.println(System.getProperty("java.class.path"));
			
			
			
			// STEP 2 is compiling .jrxml file into .jasper file
			try {
				JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
				// This is not necessary, used to test that compiling is successfull
				// JRSaver.saveObject(jasperReport, "/Users/kriz/Desktop/jasper_report_template.jasper");
				JasperReport jasperSubReport = JasperCompileManager.compileReport(subReportStream);
				//JRSaver.saveObject(jasperReport, "/Users/kriz/Desktop/subreport_01.jasper");
				JRSaver.saveObject(jasperSubReport, fileLocation+"/subreport_01.jasper");
				// STEP 3 is preparing dataSource which will fill fields in the compiled report (could be made out of bean, or directly from database)
				List<User> users = (List<User>)userDao.findAll();
				// Using right class (here JRBeanCollectionDataSource) we are creating the datasource
				// In JRBeanCollectionDataSource(listjrbean) you should add collection of objects/beans. You need to verify bean should have getter and setter for empCode.
				JRBeanCollectionDataSource usersDS = new JRBeanCollectionDataSource(users);
				// An instance of this class represents a page-oriented document that can be viewed, printed or exported to other formats.
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, usersDS);
				//jasperReport.setProperty("SubReportParam", jasperSubReport);
				// Exports a JasperReports document to PDF format. It has binary output type and exports the document to a free-form layout.
				// The JRPdfExporter implementation uses iText, which is a specialized PDF-generating library. PDF is a binary document format that allows absolute positioning of the elements inside a page, so the existing PDF exporter does not have the limitations of a grid exporter.
				// later we can set some options of the exporter,
				// We will provide JasperPrint instance to it, which got jasperReport as argument (compiled .jasper report file)
				JRPdfExporter exporter = new JRPdfExporter();
				
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

}
