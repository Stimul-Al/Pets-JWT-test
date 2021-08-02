package by.leverx.babashev.dto.person;

import by.leverx.babashev.dto.animal.AnimalCreateDto;
import by.leverx.babashev.dto.animal.AnimalPreviewDto;
import lombok.Data;

import java.util.List;

@Data
public class PersonCreateDto {

    private String name;
    private String email;
    private String password;
    private List<AnimalCreateDto> animals;
}
