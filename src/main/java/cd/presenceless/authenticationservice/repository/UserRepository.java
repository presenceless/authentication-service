package cd.presenceless.authenticationservice.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository {
    Optional<Object> findCitizenId(String username);
}
