package pr5.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация настройки безопасности
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    return http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/computers/**").access("hasRole('USER')")
            .antMatchers("/admin/**").access("hasRole('ADMIN')")
            .antMatchers("/").access("permitAll()")
            .and()
            .formLogin()
            .loginPage("/login")
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .build();
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/admin*").hasRole("ADMIN")
            .antMatchers("/home*").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}