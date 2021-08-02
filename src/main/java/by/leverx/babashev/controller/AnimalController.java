package by.leverx.babashev.controller;

import by.leverx.babashev.dto.animal.AnimalCreateDto;
import by.leverx.babashev.dto.animal.AnimalFullDto;
import by.leverx.babashev.dto.animal.AnimalPreviewDto;
import by.leverx.babashev.dto.animal.AnimalUpdateDto;
import by.leverx.babashev.service.AnimalService;
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
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/{id}")
    public AnimalFullDto findById(@PathVariable Long id) {
        return animalService.findById(id);
    }

    @GetMapping
    public List<AnimalPreviewDto> findAll() {
        return  animalService.findAll();
    }

    @PostMapping
    public AnimalFullDto create(@RequestBody AnimalCreateDto createDto) {
        return animalService.create(createDto);
    }

    @PutMapping
    public AnimalFullDto update(@RequestBody AnimalUpdateDto updateDto) {
        return animalService.update(updateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animalService.delete(id);
    }
}
