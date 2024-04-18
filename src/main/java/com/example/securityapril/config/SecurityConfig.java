package com.example.securityapril.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails student1 = User.builder()
                .username("Mahesh")
                .password(passwordEncoder.encode("mahesh"))
                .roles("STUDENT")
                .build();
        UserDetails student2 = User.builder()
                .username("Shradhha")
                .password(passwordEncoder.encode("shraddha"))
                .roles("STUDENT")
                .build();
        UserDetails admin1 = User.builder()
                .username("Pawan")
                .password(passwordEncoder.encode("pawan"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(student1,student2,admin1);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/welcome")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/student","/api/v1/admin").authenticated()
                .and().formLogin().and().build();
    }

    /**
     * DAOAuthentication provider for DB security
     */
}

