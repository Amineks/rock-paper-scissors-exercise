package com.amine.ciklumchallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsPropertiesConfiguration {

    @Value("${cors.allowed.origins}")
    private String allowedOrigins;

    @Value("${cors.allowed.headers}")
    private String allowedHeaders;

    @Value("${cors.exposed.headers}")
    private String exposedHeaders;

    @Value("${cors.allowed.methods}")
    private String allowedMethods;


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);

        corsConfiguration.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
        corsConfiguration.setAllowedHeaders(Arrays.asList(allowedHeaders.split(",")));
        corsConfiguration.setExposedHeaders(Arrays.asList(exposedHeaders.split(",")));
        corsConfiguration.setAllowedMethods(Arrays.asList(allowedMethods.split(",")));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);

    }

}
