package by.leverx.babashev.dto.animal;

import by.leverx.babashev.dto.person.PersonPreviewDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AnimalUpdateDto {

    private Long id;
    private String name;

    @JsonProperty("type_animal")
    private String typeAnimal;
    private List<PersonPreviewDto> owners;
}
