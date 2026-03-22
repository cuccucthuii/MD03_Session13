package ra.kienpc.session13.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api.kienpc.com/v1/manager")
public class ManagerController {
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String testAdmin() {
        return "Test success with role admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public String testUser() {
        return "Test success with role user";
    }
}
