package cd.presenceless.authenticationservice.services;

import cd.presenceless.authenticationservice.entity.Citizen;
import cd.presenceless.authenticationservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

// https://medium.com/@thecodebean/implementing-jwt-authentication-in-a-spring-boot-application-5a7a94d785d1
@Service
public class CitizenService {
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CitizenService(JWTService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Citizen register(Citizen citizen) {
        citizen.setPassword(passwordEncoder.encode(citizen.getPassword()));
        citizen.setDate(new Date());
        return userRepository.save(citizen);
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = Map.of(
                "sub", userName,
                "gov", true,
                "role", "attendant",
                "permissions", new String[] {"attendant:canEnroll"}
        );
        return jwtService.createToken(userName, claims);
    }

    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
