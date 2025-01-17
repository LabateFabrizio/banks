package com.example.fabrick.producingwebservice.configuration;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Servlet;

@Configuration
public class H2ConsoleConfiguration {

	
	
	@Bean
	ServletRegistrationBean<Servlet> h2ServletRegistration() {
		ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(new WebdavServlet(),
				"/h2-console/*");
		registrationBean.setLoadOnStartup(1);
		return registrationBean;
	}
	 
	 
}
