package application.supermarket.configuration;

import application.supermarket.utils.ValidationUtil;
import application.supermarket.utils.impl.ValidationUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public BufferedReader reader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
