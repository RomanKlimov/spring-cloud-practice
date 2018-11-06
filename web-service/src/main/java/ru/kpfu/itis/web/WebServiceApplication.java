package ru.kpfu.itis.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import ru.kpfu.itis.web.auth.JwtFilter;
import ru.kpfu.itis.web.config.property.MessagingProperties;

import java.util.Collections;

@SpringBootApplication
@EnableConfigurationProperties(MessagingProperties.class)
public class WebServiceApplication {
    @Value("${services.auth}")
    private String authService;

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
        registrationBean.addUrlPatterns("/ui/users", "/ui/logout");

        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }
}
