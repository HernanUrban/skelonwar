package com.urban.skelonwar.controller;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by hernan.urban on 5/20/2017.
 */
@RestControllerAdvice
public class ErrorHandlerController extends AbstractErrorHandlerController {

    /**
     * Provides the module name.
     *
     * @return the module name.
     */
    @Override
    public String getModuleName() {
        return "Skelonwar-API";
    }

}
