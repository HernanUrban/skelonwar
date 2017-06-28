package com.urban.skelonwar.controller;

import com.urban.skelonwar.error.ErrorDescription;
import com.urban.skelonwar.error.DefaultErrorCodes;
import com.urban.skelonwar.error.EntityNotFoundError;
import com.urban.skelonwar.error.SkelonwarError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by hernan.urban on 5/24/2017.
 */
public abstract class AbstractErrorHandlerController {

    private static final Logger log = LoggerFactory.getLogger(AbstractErrorHandlerController.class);

    /**
     * This method return the name of the module where the error occurred.
     *
     * @return the module name.
     */
    public abstract String getModuleName();

    /**
     * Handles generic error. Any unhandled exception will be captured by this method.
     *
     * @param request
     *          HTTP request.
     * @param exception
     *          the exception being handled.
     * @return an instance of ErrorDescription.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    public ErrorDescription handleGenericException(HttpServletRequest request, Exception exception) {
        ErrorDescription ed = ErrorDescription.buildError(HttpStatus.INTERNAL_SERVER_ERROR.value(), getModuleName(), exception,
                request);
        log.error("Generic Error: {}", ed);
        return ed;
    }

    /**
     * Handles invalid arguments
     * @param request
     * @param exception
     * @return instance of ErrorDescription
     */
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDescription processValidationError(HttpServletRequest request, MethodArgumentNotValidException exception) {
        ErrorDescription ed = ErrorDescription.buildError(HttpStatus.BAD_REQUEST.value(), getModuleName(), exception,
                request);
        log.error("Generic Error: {}", ed);
        return ed;
    }

    /**
     * Meant to handle non readable messages in posts
     * @param request
     * @param exception
     * @return instance of ErrorDescription
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDescription handleInvalidJsonTypes(HttpServletRequest request, HttpMessageNotReadableException exception) {
        ErrorDescription ed = ErrorDescription.buildError(HttpStatus.BAD_REQUEST.value(), getModuleName(), exception,
                request);
        log.error("Generic Error: {}", ed);
        return ed;
    }

    /**
     * Handles invalid argument errors.
     *
     * @param request
     *          HTTP request.
     * @param exception
     *          the exception being handled.
     * @return an instance of ErrorDescription.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ IllegalArgumentException.class })
    public ErrorDescription handleIllegalArgumentException(HttpServletRequest request, Exception exception) {
        return ErrorDescription.buildError(DefaultErrorCodes.INVALID_ARGUMENTS, getModuleName(), exception, request);
    }

    /**
     * Handles generic SkelonwarError.
     *
     * @param request
     *          HTTP request.
     * @param exception
     *          the exception being handled.
     * @return an instance of ErrorDescription.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ SkelonwarError.class })
    public ErrorDescription handleUmewinError(HttpServletRequest request, SkelonwarError exception) {
        return ErrorDescription.buildError(getModuleName(), exception, request);
    }

    /**
     * Handles entity not found errors.
     *
     * @param request
     *          HTTP request.
     * @param exception
     *          the exception being handled.
     * @return an instance of ErrorDescription.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ EntityNotFoundError.class })
    public ErrorDescription handleEntityNotFound(HttpServletRequest request, EntityNotFoundError exception) {
        return ErrorDescription.buildError(getModuleName(), exception, request);
    }

}
