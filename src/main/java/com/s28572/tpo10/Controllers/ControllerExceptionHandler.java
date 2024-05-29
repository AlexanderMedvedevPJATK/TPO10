package com.s28572.tpo10.Controllers;

import com.s28572.tpo10.Entities.Link;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleLinkNotFoundException() {
        // This method is intentionally empty.
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).header("reason", ex.getMessage()).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleConstraintViolationException(HttpServletRequest request,
                                                     ConstraintViolationException ex,
                                                     Model model) {


        if (request.getRequestURI().startsWith("/api")) {
            return ResponseEntity.badRequest().header("reason", ex.getMessage()).build();
        } else {
//            Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
//            violations.forEach(err -> {
//                model.addAttribute(
//                        err.getPropertyPath() + "Error",
//                        err.getMessage() + " (" + err.getInvalidValue() + ")"
//                );
//            });
            model.addAttribute("link", new Link());
            model.addAttribute("creating", true);
            return "link";
        }
    }
}