package com.github.chaoswang.learning.java.ood.srp.refactor;

import com.github.chaoswang.learning.java.ood.srp.Configuration;
import com.github.chaoswang.learning.java.ood.srp.ConfigurationKeys;

public class SmtpHost {
	private String smtpHost = null;
	private String altSmtpHost = null;
	private String fromAddress = null;
	private static Configuration config;
	
	public SmtpHost(){
		config = new Configuration();
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}
	
	public void sendMail() {
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
	}
}
