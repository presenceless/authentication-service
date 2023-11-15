package cd.presenceless.authenticationservice.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public Optional<Object> findCitizenId(String username) {
        return Optional.empty();
    }
}
