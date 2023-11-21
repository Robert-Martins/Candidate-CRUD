package crud.candidate.application.dtos;

import java.time.LocalDateTime;
import java.util.Date;

public record CandidateDto(
        Integer id,
        String name,
        String taxId,
        String email,
        Date birthDate,
        String intentedRole,
        String curriculum,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) { }
