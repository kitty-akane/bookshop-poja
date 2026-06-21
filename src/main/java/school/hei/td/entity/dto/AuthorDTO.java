package school.hei.td.entity.dto;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class AuthorDTO {
    private Long idAuthor;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate birthDate;
}
