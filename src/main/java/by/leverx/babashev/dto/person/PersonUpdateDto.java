package by.leverx.babashev.dto.person;

import by.leverx.babashev.dto.animal.AnimalPreviewDto;
import lombok.Data;

import java.util.List;

@Data
public class PersonUpdateDto {

    private Long id;
    private String name;
    private List<AnimalPreviewDto> animals;
}
