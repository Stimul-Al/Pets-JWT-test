package by.leverx.babashev.service;

import by.leverx.babashev.dto.person.PersonCreateDto;
import by.leverx.babashev.dto.person.PersonFullDto;
import by.leverx.babashev.dto.person.PersonPreviewDto;
import by.leverx.babashev.dto.person.PersonUpdateDto;

import java.util.List;

public interface PersonService {

    PersonFullDto findById(Long id);

    List<PersonPreviewDto> findAll();

    PersonFullDto create(PersonCreateDto createDto);

    PersonFullDto update(PersonUpdateDto updateDto);

    void delete(Long id);
}
