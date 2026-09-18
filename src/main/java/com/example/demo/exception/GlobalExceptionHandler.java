package com.example.demo.exception;

import com.example.demo.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Classe de tratamento de erros
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata os erros de procurar informações no "banco"
     * @param exception exceção lançada
     * @param request requisição
     * @return {@link ResponseEntity<ErrorResponseDTO>}
     */

    @ExceptionHandler (TarefaNaoEncontradaException.class)
    public ResponseEntity<ErrorResponseDTO> tratarTarefaNaoEncontrada (
            TarefaNaoEncontradaException exception, HttpServletRequest request) {
        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "Tarefa não encontrada",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(erro.codigo()).body(erro);
    }

    /**
     * Trata os erros de validação ,erros do usuario
     * @param ex exceção lançada
     * @param request requisição
     * @return {@link ResponseEntity<ErrorResponseDTO>}
     */

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> tratarErroValidacao(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        String mensagemErro = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() +": " +fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                mensagemErro,
                request.getRequestURI()
        );

        return ResponseEntity.status(erro.codigo()).body(erro);
    }
}
