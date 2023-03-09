package com.blitzar.cards.web.exceptionhandler;

import am.ik.yavi.core.ConstraintViolation;
import am.ik.yavi.core.ConstraintViolationsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public RestResponseEntityExceptionHandler(@Qualifier("exceptionMessageSource") MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { ConstraintViolationsException.class })
    protected ResponseEntity<?> handleConflict(ConstraintViolationsException e, WebRequest request) {

        System.out.println(request.getContextPath());
        StringBuilder sb = new StringBuilder("Violations: ");

        for (ConstraintViolation violation : e.violations()) {
            String exceptionMessage = messageSource.getMessage(violation.message(), new Object[]{}, Locale.getDefault());
            sb.append(violation.name()).append(": ").append(exceptionMessage);
        }

        return ResponseEntity.badRequest().body(sb.toString());
    }
}