package ra.kienpc.session13.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ra.kienpc.session13.entity.LoginRequest;
import ra.kienpc.session13.entity.RegisterRequest;
import ra.kienpc.session13.entity.User;
import ra.kienpc.session13.entity.dto.JwtResponse;
import ra.kienpc.session13.repository.UserRepository;
import ra.kienpc.session13.security.jwt.JwtService;
import ra.kienpc.session13.service.IUserService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Value("${jwt.expired}")
    private Long expired;

    @Override
    public void register(RegisterRequest request) {
        // check username
        if (userRepository.findByUsername(request.getUsername()).isPresent()) { // Optional Khong bao h Null
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
    
        userRepository.save(user);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            // xác minh thznh công
            String u = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("user" + u);

            String accessToken = jwtService.generateAccessToken(request.getUsername());
            String refreshToken = jwtService.generateRefreshToken(request.getUsername());

            return JwtResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .user(userRepository.findByUsername(request.getUsername()).orElseThrow())
                    .expired(new Date(new  Date().getTime() + expired))
                    .build();
    }
}
