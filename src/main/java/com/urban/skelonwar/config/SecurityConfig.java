package com.urban.skelonwar.config;

import com.urban.skelonwar.constants.GlobalConstants;
import com.urban.skelonwar.filter.JWTAuthenticationFilter;
import com.urban.skelonwar.filter.JWTLoginFilter;
import com.urban.skelonwar.security.DefaultPasswordEncoder;
import com.urban.skelonwar.security.DefaultUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by hernan.urban on 5/20/2017.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${password.secret:thisIsASecret}")
    private String pwdSecret;

    @Autowired
    DefaultUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/v2/api-docs",
                    "/swagger-resources/**", "/swagger-ui.html")
            .permitAll()
            .antMatchers(HttpMethod.POST, GlobalConstants.ACCOUNT_URI, GlobalConstants.LOGIN_URI)
            .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter(GlobalConstants.LOGIN_URI, authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(defaultPasswordEncoder());
    }

    @Bean
    public DefaultPasswordEncoder defaultPasswordEncoder() {
        return new DefaultPasswordEncoder(pwdSecret);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
