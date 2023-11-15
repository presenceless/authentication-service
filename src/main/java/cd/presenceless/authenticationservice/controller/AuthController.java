package cd.presenceless.authenticationservice.controller;

import cd.presenceless.authenticationservice.services.CitizenDetailsService;
import cd.presenceless.authenticationservice.services.JWTService;
import cd.presenceless.authenticationservice.services.OrgDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication
 * 3 forms
 * organisation, attendants, admin(government officials)
 * org jwtKeys are issued once per year
 * attendants and gov jwtKeys are issued per auth request (last for 1 day)
 * redirects are done through metadata
 * loginCred is a json object containing id and fingerprints
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTService jwtService;
    private final CitizenDetailsService citizenDetailsService;
    private final OrgDetailsService orgDetailsService;

    /**
     * Attendants
     * This method checks for the ID and fingerprints in the database
     */
    @PostMapping("/gov")
    public Object gov(@RequestBody Object loginCred) {
        return citizenDetailsService.generateToken(loginCred.toString());
    }

    /**
     * Attendants
     * This method checks for the ID and fingerprints in the database
     */
    @PostMapping("/orgs")
    public Object orgsL(@RequestBody Object loginCred) {
        return orgDetailsService.generateToken(loginCred.toString());
    }

    /**
     * This method is used to verify if the token is valid
     */
    @GetMapping("/verify")
    public boolean verify(@RequestHeader("Authorization") String token) {
        return jwtService.validateToken(token);
    }

    /**
     * Organisation
     * This method verifies if the jwt token is valid
     */
    @GetMapping("/orgs/verify")
    public boolean orgs(@RequestHeader("Authorization") String token) {
        return jwtService.validateToken(token);
    }

}
