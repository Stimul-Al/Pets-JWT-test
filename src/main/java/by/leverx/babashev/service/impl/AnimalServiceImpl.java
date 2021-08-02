package by.leverx.babashev.service.impl;

import by.leverx.babashev.dto.animal.AnimalCreateDto;
import by.leverx.babashev.dto.animal.AnimalFullDto;
import by.leverx.babashev.dto.animal.AnimalPreviewDto;
import by.leverx.babashev.dto.animal.AnimalUpdateDto;
import by.leverx.babashev.exception.EntityIsNotFoundException;
import by.leverx.babashev.repository.AnimalRepository;
import by.leverx.babashev.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.leverx.babashev.mapper.AnimalMapper.ANIMAL_MAPPER;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Override public AnimalFullDto findById(Long id) {
        return animalRepository.findById(id)
                .map(ANIMAL_MAPPER::mapToFullDto)
                .orElseThrow(() -> new EntityIsNotFoundException("Animal not Found"));
    }

    @Override public List<AnimalPreviewDto> findAll() {
        return animalRepository.findAll().stream()
                .map(ANIMAL_MAPPER::mapToPreviewDto)
                .collect(toList());
    }

    @Override public AnimalFullDto create(AnimalCreateDto createDto) {
        return null;
    }

    @Override public AnimalFullDto update(AnimalUpdateDto updateDto) {
        return null;
    }

    @Override public void delete(Long id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
        } else {
            throw new EntityIsNotFoundException("Animal not Found");
        }
    }
}
