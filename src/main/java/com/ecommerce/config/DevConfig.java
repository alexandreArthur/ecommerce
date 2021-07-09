package com.ecommerce.config;

import com.ecommerce.services.DBservice;
import com.ecommerce.services.EmailService;
import com.ecommerce.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DBservice dBservice;
    @Bean
    public boolean instatianteDataBase() throws ParseException {
        dBservice.instantiateTestDataBase();
        return true;
    }
    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
