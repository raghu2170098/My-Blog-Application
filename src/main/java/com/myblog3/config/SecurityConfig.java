package com.myblog3.config;

import com.myblog3.security.CustomUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsServices userDetailServices;
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                //.antMatchers(HttpMethod.POST, "/api/**").permitAll() // Uncomment if needed
               // .antMatchers(HttpMethod.POST,"/api/posts").hasRole("Admin")
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
//    @Bean
//    protected UserDetailsService userDetailsServices() {
//        UserDetails user1 = User.builder().username("pankaj")
//                .password(passwordEncoder().encode("password")).roles("USER").build();
//        UserDetails admin= User.builder().username("Ramu")
//                .password(passwordEncoder().encode("raghav")).roles("Admin").build();
//        UserDetails super1 = User.builder().username("ratan")
//                .password(passwordEncoder().encode("ratan")).roles("ROLE_SUPER").build();
//        return new InMemoryUserDetailsManager(user1,admin);
//    }
    //co
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailServices).
                passwordEncoder(passwordEncoder());
    }
}
