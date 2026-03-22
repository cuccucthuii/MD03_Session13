package ra.kienpc.session13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api.kienpc.com/v1/user")
public class UserController {

    @GetMapping("/test")
    public String user() {
        return "Hello USER";
    }
}
