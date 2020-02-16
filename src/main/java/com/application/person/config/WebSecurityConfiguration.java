/*
 * package com.application.person.config;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.core.Ordered; import
 * org.springframework.core.annotation.Order; import
 * org.springframework.security.config.annotation.web.builders.WebSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * @Configuration
 * 
 * @Order( value = Ordered.HIGHEST_PRECEDENCE) public class
 * WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
 * 
 * @Override public void configure(WebSecurity web) throws Exception {
 * web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
 * "/swagger-resources/**", "/configuration/security", "/swagger-ui.html",
 * "/webjars/**", "/error", "/", "/csrf"); }
 * 
 * }
 */