package ra.kienpc.session13.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api.kienpc.com/v1/admin")
public class AdminController {

    @GetMapping("/test")
    public String admin() {
        return "Hello ADMIN";
    }
}