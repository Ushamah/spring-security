package com.example.springsecurity.security.config;

import com.example.springsecurity.auth.service.ApplicationUserService;
import com.example.springsecurity.jwt.JwtTokenVerifier;
import com.example.springsecurity.jwt.JwtUsernamePasswordAuthFilter;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.springsecurity.security.ApplicationUserRole.STUDENT;


/**
 *  This is the class configuring the basic auth
 *
 */
@Api(tags = "  This is the class configuring the basic auth ")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //For annotation roles and permissions management
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // comes from the WebSecurityConfigurerAdapter which is being extended by this class
        final AuthenticationManager authenticationManager = authenticationManager();

        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Stateless session config
                .and()
                .addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager))//registering JwtUsernamePasswordAuthFilter
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernamePasswordAuthFilter.class)//registering JwtTokenVerifier afterwards
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
