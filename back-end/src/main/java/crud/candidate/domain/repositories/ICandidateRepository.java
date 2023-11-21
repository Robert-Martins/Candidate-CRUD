package crud.candidate.domain.repositories;

import crud.candidate.domain.models.Candidate;

import java.util.List;
import java.util.Optional;

public interface ICandidateRepository {

    void create(Candidate candidateDto);

    Optional<Candidate> findById(Integer id);

    List<Candidate> findAll();

    void update(Candidate candidateDto);

    void deleteById(Integer id);

    Boolean existsById(Integer id);

    Boolean existsByTaxId(String taxId);

    Boolean existsByEmail(String email);

    Boolean existsByIdAndTaxId(Integer id, String taxId);

    Boolean existsByIdAndEmail(Integer id, String email);

}
