package ra.kienpc.session13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.kienpc.session13.entity.LoginRequest;
import ra.kienpc.session13.entity.RegisterRequest;
import ra.kienpc.session13.entity.User;
import ra.kienpc.session13.service.IUserService;

@RestController
@RequestMapping("/api.kienpc.com/v1/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("Test Success",HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        User user = userService.login(request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
