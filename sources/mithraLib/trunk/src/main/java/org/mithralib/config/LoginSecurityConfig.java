package org.mithralib.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.inMemoryAuthentication()
        .withUser("frebeche")
        .password("frebeche")
        .authorities("ROLE_USER");
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
            	.antMatchers("/index.jsf").access("hasRole('ROLE_USER')")
            	.antMatchers("/css/mithra.css").anonymous()
            .and()
            .formLogin()
            	.loginPage("/loginPage.jsf")
            	.defaultSuccessUrl("/index.jsf")
            	.failureUrl("/loginPage.jsf?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
            .and()
            .logout()
            	.logoutSuccessUrl("/loginPage.jsf?logout"); 
         
    }
}
