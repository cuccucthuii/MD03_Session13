package ra.kienpc.session13.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ra.kienpc.session13.security.exception.AccessDeniedHandler;
import ra.kienpc.session13.security.exception.AuthenticationEntryPointHandler;
import ra.kienpc.session13.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity // Bật cấu hình mặc định của security
@EnableMethodSecurity // Bat phan quyen theo phuong thuc
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Tắt CSRF (csrf.disable()) vì làm REST API.
        http.csrf(csrf -> csrf.disable())
                //Cho phép truy cập tự do (permitAll) vào /api/v1/auth/**.
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api.kienpc.com/v1/auth/**").permitAll()
                                .requestMatchers("/api.kienpc.com/v1/auth/**").permitAll()
                                .requestMatchers("/api.kienpc.com/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api.kienpc.com/v1/employees").hasRole("ADMIN")
                                .requestMatchers("/api.kienpc.com/v1/user/**").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex ->
                        ex.accessDeniedHandler(new AccessDeniedHandler())
                                .authenticationEntryPoint(new AuthenticationEntryPointHandler())
                );
        return http.build();
    }


    @Bean // cung cap cơ ché xác thực
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception{
        return config.getAuthenticationManager();
    }
}
