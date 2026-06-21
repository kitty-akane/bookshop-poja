package school.hei.td.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.hei.td.entity.dto.AuthorDTO;
import school.hei.td.mapper.AuthorMapper;
import school.hei.td.repository.AuthorRepository;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        return repository.findAll().stream().map(authorMapper::toDTO).toList();
    }

    public AuthorDTO getById(Long id) {
        return repository.findById(id)
            .map(authorMapper::toDTO)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Author not found: " + id));
    }

    public AuthorDTO create(AuthorDTO dto) {
        return authorMapper.toDTO(repository.save(authorMapper.toEntity(dto)));
    }

    public AuthorDTO update(Long id, AuthorDTO dto) {
        var author = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Author not found: " + id));
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setNationality(dto.getNationality());
        author.setBirthDate(dto.getBirthDate());
        return authorMapper.toDTO(repository.save(author));
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found: " + id);
        }
        repository.deleteById(id);
    }
}
