package com.sda.study.springbootpractice.configurations;

import com.sda.study.springbootpractice.models.User;
import com.sda.study.springbootpractice.services.implementations.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.sda.study.springbootpractice.utils.Constants.Security.*;

/**
 * Configuration for Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        String role = "ROLE_";
        String admin = AUTHORITY_ADMIN.replace(role, "");
        String teacher = AUTHORITY_TEACHER.replace(role, "");
        String student = AUTHORITY_STUDENT.replace(role, "");

        httpSecurity.authorizeRequests()
                .antMatchers("/", "/user/signup/**")
                .permitAll()
                .antMatchers("/user")
                .hasRole(admin)
                .antMatchers("/school/**")
                .hasAnyRole(teacher, student, admin)
                .antMatches("/course/**")
                .hasAnyRole(student)
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .logout().permitAll(false).logoutSecurity("/")
                .and
                .csrf.disable();
    }

}
