package by.leverx.babashev.controller;

import by.leverx.babashev.dto.person.PersonCreateDto;
import by.leverx.babashev.dto.person.PersonFullDto;
import by.leverx.babashev.dto.person.PersonPreviewDto;
import by.leverx.babashev.dto.person.PersonUpdateDto;
import by.leverx.babashev.service.PersonService;
import io.honeycomb.beeline.spring.beans.aspects.ChildSpan;
import io.honeycomb.beeline.tracing.Beeline;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final Beeline beeline;

    @GetMapping("/{id}")
    public PersonFullDto findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @ChildSpan("find all persons")
    @GetMapping
    public List<PersonPreviewDto> findAll() {
        List<PersonPreviewDto> persons = personService.findAll();
        beeline.getActiveSpan().addTraceField("findPersons", persons);

        return persons;
    }

    @PostMapping
    public PersonFullDto create(@RequestBody PersonCreateDto createDto) {
        return personService.create(createDto);
    }

    @PutMapping
    public PersonFullDto update(@RequestBody PersonUpdateDto updateDto) {
        return personService.update(updateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

}
