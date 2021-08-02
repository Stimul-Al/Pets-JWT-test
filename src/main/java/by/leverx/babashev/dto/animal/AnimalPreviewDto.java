package by.leverx.babashev.dto.animal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AnimalPreviewDto {

    private Long id;
    private String name;

    @JsonProperty("type_animal")
    private String typeAnimal;
}
