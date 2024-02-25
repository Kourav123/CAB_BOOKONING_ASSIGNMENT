package com.cabbooking;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
@SpringBootApplication
@EntityScan("com.cabbooking")
public class CabBookoningAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabBookoningAssignmentApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
	    RestTemplate restTemplate = new RestTemplate();          
	    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
	    restTemplate.getMessageConverters().add(getMappingJackson2HttpMessageConverter());
	    return restTemplate;
	}
	public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
	    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
	    return mappingJackson2HttpMessageConverter;
	}

}
