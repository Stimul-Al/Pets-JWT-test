package by.leverx.babashev.service;

import by.leverx.babashev.dto.animal.AnimalCreateDto;
import by.leverx.babashev.dto.animal.AnimalFullDto;
import by.leverx.babashev.dto.animal.AnimalPreviewDto;
import by.leverx.babashev.dto.animal.AnimalUpdateDto;

import java.util.List;

public interface AnimalService {

    AnimalFullDto findById(Long id);

    List<AnimalPreviewDto> findAll();

    AnimalFullDto create(AnimalCreateDto createDto);

    AnimalFullDto update(AnimalUpdateDto updateDto);

    void delete(Long id);
}
