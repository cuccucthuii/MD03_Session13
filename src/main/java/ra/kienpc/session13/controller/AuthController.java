package ra.kienpc.session13.controller;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.kienpc.session13.entity.LoginRequest;
import ra.kienpc.session13.entity.RegisterRequest;
import ra.kienpc.session13.entity.User;
import ra.kienpc.session13.entity.dto.JwtResponse;
import ra.kienpc.session13.service.IUserService;
import ra.kienpc.session13.service.MailService;

import java.util.List;

@RestController
@RequestMapping("/api.kienpc.com/v1/auth")
public class AuthController {
    @Autowired
    private MailService mailService;
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
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        JwtResponse user = userService.login(request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestParam String refreshToken) {

        return null;
    }

    // ✅ gửi mail đơn giản
    @PostMapping("/mail")
    public String sendMailBasic(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String content
    ) {
        mailService.sendEmailNormal(to, subject, content);
        return "success";
    }

    // ✅ gửi mail kèm file
    @PostMapping("/mail-pro")
    public String sendMailWithAttachment(
            @RequestParam String to,
            @RequestParam String cc,
            @RequestParam String subject,
            @RequestParam String content,
            @RequestParam("files") MultipartFile[] files
    ) {
        try {
            mailService.sendMailPro(to, cc, subject, content, files);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
