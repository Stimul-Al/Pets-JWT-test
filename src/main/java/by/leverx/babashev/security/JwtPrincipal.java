package by.leverx.babashev.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.security.auth.Subject;
import java.security.Principal;

@AllArgsConstructor
public class JwtPrincipal implements Principal {

    @Getter
    private final String email;

    @Getter
    private final String role;

    @Override public boolean implies(Subject subject) {
        return false;
    }

    @Override public String getName() {
        return null;
    }
}
