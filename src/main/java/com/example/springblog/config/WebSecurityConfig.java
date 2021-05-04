package com.example.springblog.config;

import com.example.springblog.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/css/**", "/images/**").permitAll()
                    .antMatchers("/register", "/blogger/**").permitAll()
                    .antMatchers("/h2/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/h2-console/**/**").permitAll()
                    .antMatchers("/resources*").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successForwardUrl("/blogger")
                    .and()
                .logout()
                    .permitAll()
        ;
        /**
        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()

                .antMatchers("/css/**", "/images/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .successForwardUrl("/blogger")
                    .failureForwardUrl("/login")
                //.failureUrl("/login")
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")

        ;
**/
    }

}
