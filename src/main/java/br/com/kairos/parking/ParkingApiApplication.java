package br.com.kairos.parking;

import br.com.kairos.parking.config.property.ParkingApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(ParkingApiProperty.class)
public class ParkingApiApplication {
    
    private static ApplicationContext applicationContext;
    
    public static void main(final String[] args) {
        applicationContext = SpringApplication.run(ParkingApiApplication.class, args);
    }
    
    public static <T> T getBean(final Class<T> type) {
        return applicationContext.getBean(type);
        
    }
    
}
