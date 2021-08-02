package by.leverx.babashev.dto.person;

import by.leverx.babashev.dto.animal.AnimalPreviewDto;
import by.leverx.babashev.model.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class PersonPreviewDto {

    private Long id;
    private String name;
    private Role role;
    private List<AnimalPreviewDto> animals;
}
