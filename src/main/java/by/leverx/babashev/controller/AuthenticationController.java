package by.leverx.babashev.controller;

import by.leverx.babashev.dto.person.PersonCreateDto;
import by.leverx.babashev.dto.person.PersonFullDto;
import by.leverx.babashev.dto.security.AuthenticationResponse;
import by.leverx.babashev.dto.security.AuthorizationRequest;
import by.leverx.babashev.security.JwtTokenProvider;
import by.leverx.babashev.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final PersonService service;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthorizationRequest request) {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        authenticationManager.authenticate(auth);

        String token = jwtTokenProvider.createToken(request.getEmail());
        return new AuthenticationResponse(request.getEmail(), token);
    }

    @PostMapping("/signin")
    public PersonFullDto signIn(@RequestBody PersonCreateDto createDto) {
        return service.create(createDto);
    }
}
