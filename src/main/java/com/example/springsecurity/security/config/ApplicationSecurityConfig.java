package com.example.springsecurity.security.config;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.springsecurity.security.ApplicationUserRole.ADMIN;
import static com.example.springsecurity.security.ApplicationUserRole.STUDENT;
import static com.example.springsecurity.security.ApplicationUserRole.TRAINEE;

/**
 *  This is the class configuring the basic auth
 *
 */
@Api(tags = "  This is the class configuring the basic auth ")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) //For annotation roles and permissions management
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //              .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //            .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    /**
     *  TThis method is used to override the encoding
     *
     */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        final UserDetails ushamah = User.builder()
                .username("ush")
                .password(passwordEncoder.encode("student"))
                //.roles(STUDENT.name()) //ROLE_STUDENT
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
        final UserDetails refia = User.builder()
                .username("ref")
                .password(passwordEncoder.encode("admin"))
                //.roles(ApplicationUserRole.ADMIN.name()) //ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        final UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("trainee"))
                //.roles(TRAINEE.name()) //ROLE_TRAINEE
                .authorities(TRAINEE.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(ushamah, refia, tom);
    }
}
