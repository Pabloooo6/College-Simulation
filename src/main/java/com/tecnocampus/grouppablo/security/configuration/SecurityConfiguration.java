package com.tecnocampus.grouppablo.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/authenticate",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/coursesWithoutLessons/**").permitAll()

                                .requestMatchers("/users/messages/**").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                                .requestMatchers("/users/{id}/courses/**").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                                .requestMatchers("/users/{id}/finishedCourses").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                                .requestMatchers("/courses/searchByTitleOrDescription").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                                .requestMatchers("/courses/searchByCategoryOrLanguage").hasAnyRole("STUDENT", "TEACHER", "ADMIN")

                                .requestMatchers(HttpMethod.POST, "/courses").hasAnyRole("TEACHER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/courses/**").hasAnyRole("TEACHER", "ADMIN")
                                .requestMatchers("/courses/{id}").hasAnyRole("TEACHER", "ADMIN")
                                .requestMatchers("/courses/searchByTeacher/{teacherName}").hasAnyRole("TEACHER", "ADMIN")

                                .requestMatchers("/**").hasAnyRole( "ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}