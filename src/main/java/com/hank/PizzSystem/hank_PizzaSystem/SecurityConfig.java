package com.hank.PizzSystem.hank_PizzaSystem;


import com.hank.PizzSystem.hank_PizzaSystem.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//        .antMatchers("/admin/**").hasRole("ADMIN")
//        .antMatchers("/pizza/**").access("hasRole('ADMIN') or hasRole('USER')")
//        .antMatchers("/", "/hello").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .formLogin()
//        // .loginPage("/login")
//        .permitAll()
//        .and()
//        .logout()
//        .permitAll()
//        .and()
//        .csrf()
//        .ignoringAntMatchers("/logout");
//  }

//  @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//        .passwordEncoder(new BCryptPasswordEncoder())
//        .withUser("user")
//        .password(new BCryptPasswordEncoder()
//            .encode("123456")).roles("USER")
//        .and()
//        .withUser("admin")
//        .password(new BCryptPasswordEncoder()
//            .encode("admin")).roles("ADMIN", "USER");
//  }

  @Autowired
  private MyUserDetailsService userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/hello").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        // .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and()
        .csrf()
        .ignoringAntMatchers("/logout");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

}
