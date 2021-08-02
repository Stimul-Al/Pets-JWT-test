package by.leverx.babashev.service.impl;

import by.leverx.babashev.dto.person.PersonCreateDto;
import by.leverx.babashev.dto.person.PersonFullDto;
import by.leverx.babashev.dto.person.PersonPreviewDto;
import by.leverx.babashev.dto.person.PersonUpdateDto;
import by.leverx.babashev.exception.EntityIsNotFoundException;
import by.leverx.babashev.mapper.PersonMapper;
import by.leverx.babashev.model.Person;
import by.leverx.babashev.model.enums.Role;
import by.leverx.babashev.repository.PersonRepository;
import by.leverx.babashev.security.JwtTokenProvider;
import by.leverx.babashev.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static by.leverx.babashev.mapper.PersonMapper.PERSON_MAPPER;
import static by.leverx.babashev.model.enums.Role.USER;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder encoder;

    @Override public PersonFullDto findById(Long id) {
        return personRepository.findById(id)
                .map(PERSON_MAPPER::mapToFullDto)
                .orElseThrow(() -> new EntityIsNotFoundException("User not Found"));
    }

    @Override public List<PersonPreviewDto> findAll() {
        return personRepository.findAll().stream()
                .map(PERSON_MAPPER::mapToPreviewDto)
                .collect(toList());
    }

    @Override public PersonFullDto create(PersonCreateDto createDto) {
        Person personToSave = PERSON_MAPPER.mapToEntity(createDto);

        getCheckExistingUser(personToSave);

        String encodedPassword = encoder.encode(createDto.getPassword());

        personToSave.setPassword(encodedPassword);
        personToSave.setRole(USER);

        Person savedPerson = personRepository.save(personToSave);

        return PERSON_MAPPER.mapToFullDto(savedPerson);
    }

    private void getCheckExistingUser(Person personToSave) {
        if (personRepository.existsPersonByEmail(personToSave.getEmail())) {
            throw new EntityIsNotFoundException("This email user is exist!");
        }
    }

    @Override public PersonFullDto update(PersonUpdateDto updateDto) {
        return null;
    }

    @Override public void delete(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new EntityIsNotFoundException("User not found");
        }
    }
}
