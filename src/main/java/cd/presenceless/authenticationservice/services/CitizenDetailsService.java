package cd.presenceless.authenticationservice.services;

import cd.presenceless.authenticationservice.model.CitizenDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CitizenDetailsService implements UserDetailsService {
    private final JWTService jwtService;

    public CitizenDetailsService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CitizenDetails();
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = Map.of(
                "sub", userName,
                "gov", true,
                "role", "attendant",
                "permissions", new String[] {"attendant:canEnroll"}
        );
        return jwtService.createToken(claims, userName);
    }
}
