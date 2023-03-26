package shop.mtcoding.rodongin.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.mtcoding.rodongin.config.filter.JwtVerifyFilter;

@Configuration
public class FilterRegisterConfig {

    @Bean
    public FilterRegistrationBean<?> jwtVerifyFilterAdd() {
        FilterRegistrationBean<JwtVerifyFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new JwtVerifyFilter());
        registration.addUrlPatterns("/s/*");
        registration.setOrder(1);

        return registration;
    }
}
