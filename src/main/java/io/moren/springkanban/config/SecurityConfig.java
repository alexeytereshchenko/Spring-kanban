package io.moren.springkanban.config;

import io.moren.springkanban.exception.RestAccessDeniedHandler;
import io.moren.springkanban.exception.RestAuthenticationEntryPoint;
import io.moren.springkanban.security.JwtFilter;
import io.moren.springkanban.service.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;
    private final DetailsService detailsService;
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .mvcMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated();
    }
}
