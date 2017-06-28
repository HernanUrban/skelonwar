package com.urban.skelonwar.error;

/**
 * Created by hernan.urban on 5/24/2017.
 */
public class SkelonwarError extends RuntimeException {

    private static final long serialVersionUID = 6357548932897618816L;
    private final int errorCode;

    public SkelonwarError() {
        super();
        this.errorCode = DefaultErrorCodes.GENERIC_ERROR;
    }

    public SkelonwarError(String message) {
        super(message);
        this.errorCode = DefaultErrorCodes.GENERIC_ERROR;
    }

    public SkelonwarError(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = DefaultErrorCodes.GENERIC_ERROR;
    }

    public SkelonwarError(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public SkelonwarError(int errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    /**
     * Retrieves the error code.
     *
     * @return the error code of the error.
     */
    public int getErrorCode() {
        return errorCode;
    }
}
