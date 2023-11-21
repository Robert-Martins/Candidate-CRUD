package crud.candidate.infrastructure.exceptions.handler;

import crud.candidate.infrastructure.exceptions.DuplicateKeyException;
import crud.candidate.infrastructure.exceptions.details.ExceptionDetails;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ExceptionHandler {

    private static final Integer BAD_REQUEST_STATUS = 400;

    private static final Integer SERVER_ERROR_STATUS = 500;

    public static ExceptionDetails handleException(DuplicateKeyException exception) {
        return ExceptionDetails.builder()
                .title("Chave Duplicada")
                .status(BAD_REQUEST_STATUS)
                .details(exception.getMessage())
                .developerMessage(DuplicateKeyException.EXCEPTION_DEVELOPER_MESSAGE)
                .className(Arrays.stream(exception.getStackTrace()).findFirst().get().getClassName())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ExceptionDetails handleException(ServletException exception) {
        return ExceptionDetails.builder()
                .title("Erro no Servidor")
                .status(SERVER_ERROR_STATUS)
                .details(exception.getMessage())
                .developerMessage("Jetty Server Error")
                .className(Arrays.stream(exception.getStackTrace()).findFirst().get().getClassName())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ExceptionDetails handleException(IOException exception) {
        return ExceptionDetails.builder()
                .title("Erro de I/O")
                .status(SERVER_ERROR_STATUS)
                .details(exception.getMessage())
                .developerMessage("Error while attempting to manipulate Byte Data")
                .className(Arrays.stream(exception.getStackTrace()).findFirst().get().getClassName())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
