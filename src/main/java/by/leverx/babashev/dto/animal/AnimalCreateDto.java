package by.leverx.babashev.dto.animal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AnimalCreateDto {

    private String name;

    @JsonProperty("type_animal")
    private String typeAnimal;
}
