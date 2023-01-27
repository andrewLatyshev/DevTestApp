package com.test.developertest.config;

import com.test.developertest.models.FilesStorage;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
@EnableConfigurationProperties({FilesStorage.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**", "/index**").hasAnyRole("CLIENT", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutUrl("/logout").permitAll();
    }


    @Bean
    public UserDetailsService user() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$hq6ezOLDgfXIQWBZamJdPOTQoRboIemsKGAuBNauTGQubT5s2/5kC")
                .roles("ADMIN", "CLIENT")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }


    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_XML);
    }

}
