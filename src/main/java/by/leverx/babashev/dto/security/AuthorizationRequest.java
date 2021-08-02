package by.leverx.babashev.dto.security;

import lombok.Data;

@Data
public class AuthorizationRequest {

    private String email;
    private String password;
}
