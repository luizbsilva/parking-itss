package br.com.kairos.parking.config;

import br.com.kairos.parking.config.property.ParkingApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    
    @Autowired
    private ParkingApiProperty property;
    
    @Bean
    public JavaMailSender javaMailSender() {
        final Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.connectiontimeout", 10000);
        
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(props);
        mailSender.setHost(this.property.getMail().getHost());
        mailSender.setPort(this.property.getMail().getPort());
        mailSender.setUsername(this.property.getMail().getUsername());
        mailSender.setPassword(this.property.getMail().getPassword());
        
        return mailSender;
    }
}
