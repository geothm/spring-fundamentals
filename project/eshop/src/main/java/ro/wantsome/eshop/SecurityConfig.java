package ro.wantsome.eshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/error/**", "/login", "/login/**", "/home").permitAll()
                        .requestMatchers("/orderForm","/addOrder", "/updateOrder/**", "/deleteOrder/**").hasAnyRole("MODIFY_ORDERS", "ADMIN")
                        .requestMatchers("/products").hasAnyRole("PRODUCTS", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .permitAll()
                        .defaultSuccessUrl("/home")// Use the default login page provided by Spring Security
                )
                .logout(logout -> logout
                        .permitAll() // Allow anyone to log out
                )
                .csrf(csrf -> csrf.disable());// Disable CSRF for testing purposes, remove in production

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("andreea")
                .password("password")
                .roles("PRODUCTS")
                .build());

        manager.createUser(User.withDefaultPasswordEncoder()
                .username("cristi")
                .password("password")
                .roles("MODIFY_ORDERS")
                .build());

        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build());

        return manager;
    }
}
