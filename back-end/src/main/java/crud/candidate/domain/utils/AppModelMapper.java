package crud.candidate.domain.utils;

import crud.candidate.application.dtos.CandidateDto;
import crud.candidate.domain.models.Candidate;

public class AppModelMapper {

    public static Candidate mapDtoToModel(CandidateDto candidateDto) {
        return new Candidate(
                candidateDto.id(),
                candidateDto.name(),
                candidateDto.taxId(),
                candidateDto.email(),
                candidateDto.birthDate(),
                candidateDto.intentedRole(),
                candidateDto.curriculum(),
                candidateDto.updatedAt(),
                candidateDto.createdAt()
        );
    }

    public static CandidateDto mapModelToDto(Candidate candidate) {
        return new CandidateDto(
                candidate.getId(),
                candidate.getName(),
                candidate.getTaxId(),
                candidate.getEmail(),
                candidate.getBirthDate(),
                candidate.getIntentedRole(),
                candidate.getCurriculum(),
                candidate.getUpdatedAt(),
                candidate.getCreatedAt()
        );
    }

}
