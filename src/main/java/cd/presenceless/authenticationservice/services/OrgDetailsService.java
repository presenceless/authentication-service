package cd.presenceless.authenticationservice.services;

import cd.presenceless.authenticationservice.model.OrgDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrgDetailsService implements UserDetailsService {
    private final JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new OrgDetails();
    }

    public Map<String, Object> generateToken(String orgName) {
        Map<String, Object> sandboxClaims = new HashMap<>();
        sandboxClaims.put("sandbox", true);
        sandboxClaims.put("requests limit", 100);

        Map<String, Object> productionClaims = new HashMap<>();
        productionClaims.put("production", true);

        return Map.of(
                "sandbox", jwtService.createToken(sandboxClaims, orgName),
                "production", jwtService.createToken(productionClaims, orgName)
        );
    }
}
