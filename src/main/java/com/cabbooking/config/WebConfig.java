/*
 * package com.cabbooking.config;
 * 
 * import java.time.Duration;
 * 
 * import org.springframework.boot.web.servlet.FilterRegistrationBean; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.CacheControl; import
 * org.springframework.web.servlet.config.annotation.EnableWebMvc; import
 * org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 * import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 * import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
 * 
 * @Configuration
 * 
 * @EnableWebMvc public class WebConfig implements WebMvcConfigurer {
 * 
 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
 * registry.addResourceHandler("/resources/**") .addResourceLocations("/public",
 * "classpath:/static/")
 * .setCacheControl(CacheControl.maxAge(Duration.ofDays(365))); }
 * 
 * @Bean public FilterRegistrationBean<ResourceUrlEncodingFilter>
 * resourceUrlEncodingFilter() {
 * FilterRegistrationBean<ResourceUrlEncodingFilter> filterRegBean = new
 * FilterRegistrationBean<>(); filterRegBean.setFilter(new
 * ResourceUrlEncodingFilter()); filterRegBean.addUrlPatterns("/css/*",
 * "/js/*"); // Adjust patterns accordingly return filterRegBean; }
 * 
 * }
 */