package by.leverx.babashev.mapper;

import by.leverx.babashev.dto.animal.AnimalCreateDto;
import by.leverx.babashev.dto.animal.AnimalFullDto;
import by.leverx.babashev.dto.animal.AnimalPreviewDto;
import by.leverx.babashev.model.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper/*(componentModel = "spring")*/
public interface AnimalMapper {

    AnimalMapper ANIMAL_MAPPER = Mappers.getMapper(AnimalMapper.class);

    Animal mapToEntity(AnimalCreateDto createDto);

    Animal mapToEntity(AnimalPreviewDto previewDto);

    AnimalFullDto mapToFullDto(Animal animal);

    AnimalPreviewDto mapToPreviewDto(Animal animal);

    List<Animal> mapToListAnimal(List<AnimalCreateDto> animalsCreateDto);
}
