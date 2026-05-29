package com.aulaspring.aulajpa.configs;

import com.aulaspring.aulajpa.dto.ApiErrorDTO;
import com.aulaspring.aulajpa.dto.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationControllerAdvice {
    @ExceptionHandler(RegraNegocioException.class)
    public ApiErrorDTO handleRegraNegocioException(RegraNegocioException ex) {
        String msg = ex.getMessage();
        return new ApiErrorDTO(msg);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorDTO validationError(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(
                        Collectors.toList());
        return new ApiErrorDTO(erros);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorDTO handleGenericException(Exception ex) {
        return new ApiErrorDTO("Erro interno no servidor");
    }


}
