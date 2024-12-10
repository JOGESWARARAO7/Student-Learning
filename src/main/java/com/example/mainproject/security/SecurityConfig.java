package com.example.mainproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and()
            .csrf().disable() // Disabling CSRF as you are using it with Angular
            .authorizeRequests()
            .requestMatchers("/solve/signup", "/solve/otpChecked","/solve/login","/solve/getallpdfproblem","/problem/problemsignup","/problem/otpChecked","/problem/login","/problem/pupupload","/problem/getuploadproblem","/problem/uploadprofile","/problem/getinbultpuserprofile","/problem/getmech","/problem/getcivil","/problem/geteee","/problem/getcse").permitAll() // Ensure these paths are allowed
            .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}


