package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by simonhamermesh on 3/24/16.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
    }

    @Configuration
    protected static class AuthenticationConfiguration extends
            GlobalAuthenticationConfigurerAdapter{

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception{
        //This configuration lets you specify which groups you search through for matches. Check
            // the .ldif file for this structure.  This set up will only search through
            // space cadets.  I have added one more space cadet than the original demo file.
            auth.ldapAuthentication().userDnPatterns("uid={0}, ou=space cadets")
                    .groupSearchBase("ou=groups")
                    .contextSource().ldif("classpath:test-server.ldif");

            auth.ldapAuthentication().userDnPatterns("uid={0}, ou = people").groupSearchBase("ou=groups")
                    .contextSource().ldif("classpath:test-server.ldif");
        }
    }


}
