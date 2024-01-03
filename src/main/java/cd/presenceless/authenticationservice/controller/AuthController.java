package cd.presenceless.authenticationservice.controller;

import cd.presenceless.authenticationservice.entity.Citizen;
import cd.presenceless.authenticationservice.services.CitizenService;
import cd.presenceless.authenticationservice.services.OrganisationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
public class AuthController {

    private final CitizenService citizenService;
    private final OrganisationService organisationService;
    private final AuthenticationManager authenticationManager;

    public AuthController(CitizenService citizenService, OrganisationService organisationService, AuthenticationManager authenticationManager) {
        this.citizenService = citizenService;
        this.organisationService = organisationService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Admin
     * This method registers the government officials
     */
    @PostMapping("/register")
    public Object register(@RequestBody Citizen citizen) {
        return citizenService.register(citizen);
    }

    /**
     * Attendants
     * This method checks for the ID and fingerprints in the database
     */
    @PostMapping("/gov")
    public Object gov(@RequestBody Citizen citizen) {
        final var authManager = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(citizen.getCid(), citizen.getPassword())
        );

        if (!authManager.isAuthenticated()) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        return citizenService.generateToken(citizen.getCid());
    }

    /**
     * This method is used to verify if the token is valid
     */
    @GetMapping("/verify")
    public boolean verify(@RequestHeader("Authorization") String token) {
        return citizenService.validateToken(token);
    }

    /**
     * Organisation
     * This method verifies if the jwt token is valid
     */
    @GetMapping("/orgs/verify")
    public boolean orgs(@RequestHeader("Authorization") String token) {
        return organisationService.validateToken(token);
    }
}
