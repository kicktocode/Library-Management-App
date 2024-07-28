package com.example.library_app.Configuration;

import com.example.library_app.security.JwtAccessDeniedHandler;
import com.example.library_app.security.JwtAuthenticationEntryPoint;
import com.example.library_app.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity
public class SecurityConfig  {
    private  final JwtFilter jwtFilter;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    @Bean
    public AuthenticationManager authenticationManager(final
                                                       AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception

    {
         http
                .headers(headers->headers.frameOptions(frameOptions-> frameOptions.disable()))
                .csrf(csrf-> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/admin").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/user").hasAnyAuthority("ADMIN","USER")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(httpBasic-> httpBasic.disable())
                .exceptionHandling(exceptionHandling->exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(sessionManagement ->sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer()
    {
        return (web ->web.ignoring().requestMatchers("/api/public","/api/auth/login","api/auth/signup") );
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            public void addCorsMapping(CorsRegistry registry)
            {
                registry.addMapping("/**").allowedMethods("*");

            }
        };
    }

}
