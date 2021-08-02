package by.leverx.babashev.service.impl;

import by.leverx.babashev.model.Person;
import by.leverx.babashev.repository.PersonRepository;
import by.leverx.babashev.security.JwtPersonDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtPersonDetailService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(s).orElseThrow(() -> new RuntimeException("User not found"));

        return new JwtPersonDetails(person);
    }
}
