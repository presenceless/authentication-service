package cd.presenceless.authenticationservice.repository;

import cd.presenceless.authenticationservice.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<Citizen, Long> {
    Optional<Citizen> findByCid(String cid);
}
