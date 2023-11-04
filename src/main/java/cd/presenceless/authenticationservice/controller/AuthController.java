package cd.presenceless.authenticationservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    /**
     * Authentication
     * 3 forms
     * organisation, attendants, admin(government officials)
     * org jwtKeys are issued once per year
     * attendants and gov jwtKeys are issued per auth request (last for 1 day)
     * redirects are done through metadata
     */
    @PostMapping
    public Object login(@RequestBody Object loginCred) {
        return loginCred;
    }

    @PostMapping("/orgs")
    public Object orgs(@RequestBody Object loginCred) {
        return loginCred;
    }

    @PostMapping("/gov")
    public Object gov(@RequestBody Object loginCred) {
        return loginCred;
    }
}
