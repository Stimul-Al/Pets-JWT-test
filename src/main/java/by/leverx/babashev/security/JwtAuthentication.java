package by.leverx.babashev.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

@AllArgsConstructor
public class JwtAuthentication implements Authentication {

    private final JwtPrincipal principal;
    private boolean authenticated;

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(principal.getRole()));
    }

    @Override public Object getCredentials() {
        return null;
    }

    @Override public Object getDetails() {
        return null;
    }

    @Override public Object getPrincipal() {
        return principal;
    }

    @Override public boolean isAuthenticated() {
        return authenticated;
    }

    @Override public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override public String getName() {
        return null;
    }
}
