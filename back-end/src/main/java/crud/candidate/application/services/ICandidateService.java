package crud.candidate.application.services;

import crud.candidate.application.dtos.CandidateDto;

import java.util.List;

public interface ICandidateService {

    void create(CandidateDto candidateDto);

    CandidateDto findById(Integer id);

    List<CandidateDto> findAll();

    void update(CandidateDto candidateDto);

    void deleteById(Integer id);

}
