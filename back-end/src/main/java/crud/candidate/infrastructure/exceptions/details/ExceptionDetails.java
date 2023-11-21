package crud.candidate.infrastructure.exceptions.details;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ExceptionDetails {

    private String title;

    private int status;

    private String details;

    private String developerMessage;

    private String className;

    private LocalDateTime timestamp;

}