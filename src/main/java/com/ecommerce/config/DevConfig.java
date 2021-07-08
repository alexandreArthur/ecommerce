package com.ecommerce.config;

import com.ecommerce.services.EmailService;
import com.ecommerce.services.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
