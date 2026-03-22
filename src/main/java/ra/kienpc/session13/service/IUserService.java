package ra.kienpc.session13.service;

import ra.kienpc.session13.entity.LoginRequest;
import ra.kienpc.session13.entity.RegisterRequest;
import ra.kienpc.session13.entity.User;
import ra.kienpc.session13.entity.dto.JwtResponse;

public interface IUserService {
    void register(RegisterRequest request);
    JwtResponse login(LoginRequest request);
}
