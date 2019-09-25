package com.company.security;

/**
 * Created by ponomarev_ia on 05.09.2019.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private DataSource dataSource;

    // Authentication : User --> Roles
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, active from USERS.USER_TABLE where username=?")
                .authoritiesByUsernameQuery("select u.username, u.role from USERS.USER_TABLE u where username=?");
    }

    // Authorization : Role -> Access
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/users/accept").permitAll()
                .antMatchers("/users/worker/*").hasAuthority("USER")
                .antMatchers("/users/index").hasAuthority("USER")/*permitAll()*/
                .antMatchers("/users/admin/*").hasAuthority("ADMIN")
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .formLogin()
                .and()
                .logout()
                .permitAll();
    }

}