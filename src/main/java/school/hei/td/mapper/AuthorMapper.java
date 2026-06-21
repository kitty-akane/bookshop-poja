package school.hei.td.mapper;

import org.springframework.stereotype.Component;
import school.hei.td.entity.Author;
import school.hei.td.entity.dto.AuthorDTO;

@Component
public class AuthorMapper {

    public AuthorDTO toDTO(Author author) {
        return AuthorDTO.builder()
            .idAuthor(author.getIdAuthor())
            .firstName(author.getFirstName())
            .lastName(author.getLastName())
            .nationality(author.getNationality())
            .birthDate(author.getBirthDate())
            .build();
    }

    public Author toEntity(AuthorDTO dto) {
        return Author.builder()
            .idAuthor(dto.getIdAuthor())
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .nationality(dto.getNationality())
            .birthDate(dto.getBirthDate())
            .build();
    }
}
