package ra.kienpc.session13.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Bật cấu hình mặc định của security
public class SecurityConfig {
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
                                .anyRequest().authenticated()
                );
        return http.build();
    }


    @Bean // cung cap cơ ché xác thực
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception{
        return config.getAuthenticationManager();
    }
}
