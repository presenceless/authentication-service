package cd.presenceless.authenticationservice.services;

import cd.presenceless.authenticationservice.model.CitizenDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CitizenDetailsService implements UserDetailsService {
    private final JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CitizenDetails();
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("gov", true);
        claims.put("role", "attendant");
        claims.put("permissions", new String[]{"official:canEnroll"});
        return jwtService.createToken(claims, userName);
    }
}
