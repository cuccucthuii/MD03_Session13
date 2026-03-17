package ra.kienpc.session13.service;

import ra.kienpc.session13.entity.LoginRequest;
import ra.kienpc.session13.entity.RegisterRequest;
import ra.kienpc.session13.entity.User;

public interface IUserService {
    void register(RegisterRequest request);
    User login(LoginRequest request);
}
