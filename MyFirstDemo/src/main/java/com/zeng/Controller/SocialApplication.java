package com.zeng.Controller;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Principal;
import java.util.logging.Filter;

/**
 * @author Administrator
 */

@Configuration
@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableSwagger2
@EnableOAuth2Client
@RestController
public class SocialApplication extends WebSecurityConfigurerAdapter {

  @RequestMapping("/user")
  public Principal user(Principal principal) {
    return principal;
  }

  @Autowired
  OAuth2ClientContext oauth2ClientContext;

  private Filter ssoFilter() {
    OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
    OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), oauth2ClientContext);
    facebookFilter.setRestTemplate(facebookTemplate);
    UserInfoTokenServices tokenServices = new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId());
    tokenServices.setRestTemplate(facebookTemplate);
    facebookFilter.setTokenServices(tokenServices);
    return facebookFilter;
  }
  @Bean
  @ConfigurationProperties("facebook.client")
  public AuthorizationCodeResourceDetails facebook() {
    return new AuthorizationCodeResourceDetails();
  }

  @Bean
  @ConfigurationProperties("facebook.resource")
  public ResourceServerProperties facebookResource() {
    return new ResourceServerProperties();
  }

  @Bean
  public FilterRegistrationBean oauth2ClientFilterRegistration(
          OAuth2ClientContextFilter filter) {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(filter);
    registration.setOrder(-100);
    return registration;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/", "/login**", "/webjars/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and().logout().logoutSuccessUrl("/").permitAll()
            .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}