package com.it.app.config;

import com.it.app.security.filter.AuthenticationTokenFilter;
import com.it.app.service.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    /**
     * Creates Bean from AuthenticationManager that processes an authentication request
     *
     * @return - AuthenticationManager
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .authorizeRequests()
                .mvcMatchers("/authentication/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/courses/**", "/tutors/**", "/languages/**", "/levels/**", "/types/**", "/events/**").permitAll()
                .mvcMatchers("/studentCourses/enroll", "/events/enroll/**").hasRole("STUDENT")
                .mvcMatchers("/courses/**", "events/**", "/studentCourses/**").hasRole("MAKER")
                .mvcMatchers("/roles/**", "/users/**", "/students/**", "/scheduleMakers/**", "/events/**", "/languages/**", "/levels/**", "/types/**", "/tutors/**", "/courses/**", "/studentCourses/**").hasRole("ADMIN")
                .anyRequest().hasRole("ADMIN");
        http.addFilterBefore(new AuthenticationTokenFilter(tokenService, userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
}
