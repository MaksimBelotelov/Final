package org.belotelov.diplom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/nom/**").hasRole("MANAGER")
                                .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults()).exceptionHandling().accessDeniedPage("/accessdenided");
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails manager = User.builder().username("manager").password("{noop}qwerty").roles("MANAGER").build();
        UserDetails employee = User.builder().username("employee").password("{noop}12345").roles("EMPLOYEE").build();

        return new InMemoryUserDetailsManager(manager, employee);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
