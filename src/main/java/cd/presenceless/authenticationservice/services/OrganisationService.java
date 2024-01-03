package cd.presenceless.authenticationservice.services;

import cd.presenceless.authenticationservice.entity.Organisation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrganisationService {
    private final JWTService jwtService;

    public Map<String, Object> generateToken(String orgName) {
        Map<String, Object> sandboxClaims = new HashMap<>();
        sandboxClaims.put("sandbox", true);
        sandboxClaims.put("requests limit", 100);

        Map<String, Object> productionClaims = new HashMap<>();
        productionClaims.put("production", true);

        return Map.of(
                "sandbox", jwtService.createToken(orgName, sandboxClaims),
                "production", jwtService.createToken(orgName, productionClaims)
        );
    }

    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
