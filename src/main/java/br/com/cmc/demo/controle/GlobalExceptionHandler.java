package br.com.cmc.demo.controle;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleValidationException(ConstraintViolationException ex, Model model) {
        List<String> errors = ex.getConstraintViolations().stream()
            .map(violation -> violation.getMessage())
            .collect(Collectors.toList());
        model.addAttribute("validationErrors", errors);
        return "formPage";  // Redireciona para a página do formulário com os erros
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Ocorreu um erro inesperado. Por favor, tente novamente.");
        return "errorPage";  // Página de erro genérica
    }
}
