package cd.presenceless.authenticationservice.services;

import cd.presenceless.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param cid username of the user
     * @return UserDetails
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String cid) throws UsernameNotFoundException {
        return userRepository.findByCid(cid)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
