package pe.com.emilima.dms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.com.emilima.dms.security.CustomAuthenticationProvider;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class WebSecurityConfig {

    protected final Logger logger = Logger.getLogger(getClass().getName());
    @Autowired
    private CustomAuthenticationProvider authProvider;

    // Sets the default authentication manager, which is customized
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Order(value = 1)
    @Bean
    public SecurityFilterChain publicResourcesFilterChain(HttpSecurity http) throws Exception {
        logger.log(Level.INFO, "Accessing to public resources...");
        http.
                antMatcher("/public/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .permitAll()
                )
                .httpBasic(withDefaults());

        return http.build();
    }

    @Order(value = 2)
    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        logger.log(Level.INFO, "Authenticating...");
        http
                .authorizeRequests(authorize -> authorize
                        .anyRequest()
                        .authenticated()
                )

                // Sets the default login page
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )

                // Sets the default logout way
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .csrf().disable()
                .httpBasic();

        return http.build();
    }
}
