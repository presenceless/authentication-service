package cd.presenceless.authenticationservice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;

public class Organisation implements UserDetails {
    private String name, regNumber;
    private Address address;
    private Date date;
    private boolean is_deleted, is_approved;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !is_deleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !is_approved;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return is_approved;
    }
}
