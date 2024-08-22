package ro.wantsome.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ro.wantsome.security.domain.UserJpaRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private static final String H2_CONSOLE_PATH = "/h2-console/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/error/**", "/login", "/login/**", "/register", "/books", "/books/**", "/students/**").permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/accounts")).hasRole("FINANCIAL")
                        .requestMatchers(new AntPathRequestMatcher("/products")).hasRole("PRODUCTS")
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
    public CustomUserDetailsService customUserDetailsService(UserJpaRepository userJpaRepository) {
        return new CustomUserDetailsService(userJpaRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher(H2_CONSOLE_PATH));
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user1")
                .password("password")
                .roles("PRODUCTS")
                .build());

        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user2")
                .password("password")
                .roles("FINANCIAL")
                .build());

        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user3")
                .password("password")
                .roles("FINANCIAL", "PRODUCTS")
                .build());

        return manager;
    }*/
}
