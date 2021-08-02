package by.leverx.babashev.mapper;

import by.leverx.babashev.dto.person.PersonCreateDto;
import by.leverx.babashev.dto.person.PersonFullDto;
import by.leverx.babashev.dto.person.PersonPreviewDto;
import by.leverx.babashev.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AnimalMapper.class/*, componentModel = "spring"*/)
public interface PersonMapper {

    PersonMapper PERSON_MAPPER = Mappers.getMapper(PersonMapper.class);

    Person mapToEntity(PersonCreateDto createDto);

    PersonFullDto mapToFullDto(Person person);

    PersonPreviewDto mapToPreviewDto(Person person);
}
