package com.ftc.exception;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice()
@Generated
@Slf4j
public class ErrorAdvice {

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> notFoundException(NotFoundException exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Resource not found", details);
		ResponseEntity<Object> errorResponse = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		log.warn("Resource not found: ", exception);
		return errorResponse;
	}
	
	@ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> badRequestException(BadRequestException exception, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Input data error", details);
        ResponseEntity<Object> errorResponse = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        log.error("Input data error: ", exception.getLocalizedMessage());
        return errorResponse;
    }

	@ExceptionHandler(ConflictException.class)
	public final ResponseEntity<Object> conflictException(ConflictException exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Conflict", details);
		ResponseEntity<Object> errorResponse = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		log.warn("Conflict: ", exception.getLocalizedMessage());
		return errorResponse;
	}

	@ExceptionHandler(InternalException.class)
	public final ResponseEntity<Object> internalException(InternalException exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Internal error", details);
		log.error("Internal error: ", exception.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("Argument not valid", details);
	    log.warn("Argument not valid: ", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public final ResponseEntity<Object> methodConstraintViolation(ConstraintViolationException exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ConstraintViolation<?> error : exception.getConstraintViolations()) {
			details.add(error.getMessage());
		}
		ErrorResponse error = new ErrorResponse("Constraint violation", details);
		log.error("Constraint violation: ", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = BindException.class)
	public final ResponseEntity<Object> bindExceptionException(BindException exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("Bind exception", details);
		log.error("Bind exception: ", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public final ResponseEntity<Object> handleException(HttpMessageNotReadableException exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add("The method you are querying requires a valid JSON in the body of the Http request");
		ErrorResponse error = new ErrorResponse("Input data error: ", details);
		log.error("Input JSON error: ", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NumberFormatException.class)
	public final ResponseEntity<Object> handleException(NumberFormatException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add("The method you are querying requires a number or a list of numbers as a parameter");
		ErrorResponse error = new ErrorResponse("Input data error: ", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
