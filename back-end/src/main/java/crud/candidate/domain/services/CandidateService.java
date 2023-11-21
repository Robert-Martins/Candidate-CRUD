package crud.candidate.domain.services;

import crud.candidate.application.dtos.CandidateDto;
import crud.candidate.application.services.ICandidateService;
import crud.candidate.domain.models.Candidate;
import crud.candidate.domain.repositories.ICandidateRepository;
import crud.candidate.domain.utils.AppModelMapper;
import crud.candidate.domain.utils.Functions;
import crud.candidate.infrastructure.exceptions.DuplicateKeyException;
import crud.candidate.infrastructure.exceptions.NotFoundException;
import crud.candidate.infrastructure.repositories.CandidateRepository;

import java.util.List;
import java.util.Optional;

public class CandidateService implements ICandidateService {

    private final ICandidateRepository candidateRepository = CandidateRepository.getInstance();

    private static ICandidateService candidateService;

    @Override
    public void create(CandidateDto candidateDto) {
        if(this.candidateRepository.existsByTaxId(candidateDto.taxId()))
            throw new DuplicateKeyException("Candidate with Tax ID already registered");
        if(this.candidateRepository.existsByEmail(candidateDto.email()))
            throw new DuplicateKeyException("Candidate with Email already registered");
        this.candidateRepository.create(AppModelMapper.mapDtoToModel(candidateDto));
    }

    @Override
    public CandidateDto findById(Integer id) {
        return AppModelMapper.mapModelToDto(
                candidateRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Candidato não encontrado"))
        );
    }

    @Override
    public List<CandidateDto> findAll() {
        return candidateRepository.findAll()
                .stream()
                .map(AppModelMapper::mapModelToDto)
                .toList();
    }

    @Override
    public void update(CandidateDto candidateDto) {
        if(candidateRepository.existsByIdAndTaxId(candidateDto.id(), candidateDto.taxId()))
            throw new DuplicateKeyException("Candidate with Tax ID already registered");
        if(candidateRepository.existsByIdAndEmail(candidateDto.id(), candidateDto.email()))
            throw new DuplicateKeyException("Candidate with Email already registered");
        candidateRepository.update(AppModelMapper.mapDtoToModel(candidateDto));
    }

    @Override
    public void deleteById(Integer id) {
        Functions.acceptTrue(
                this.candidateRepository.existsById(id),
                () -> candidateRepository.deleteById(id)
        );
    }

    public static ICandidateService getInstance() {
        candidateService = Optional.ofNullable(candidateService).isEmpty() ? new CandidateService() : candidateService;
        return candidateService;
    }

}
