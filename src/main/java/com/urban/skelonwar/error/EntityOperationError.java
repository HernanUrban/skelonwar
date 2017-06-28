package com.urban.skelonwar.error;

import static com.urban.skelonwar.error.DefaultErrorCodes.ACCOUNT_TRANSACTION_ERROR;

/**
 * Created by hernan.urban on 5/24/2017.
 */
public class EntityOperationError extends SkelonwarError {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3477150493478065071L;

	public EntityOperationError() {
        super(ACCOUNT_TRANSACTION_ERROR, "Unable to perform operation.");
    }

    public EntityOperationError(String message) {
        super(ACCOUNT_TRANSACTION_ERROR, message);
    }

    public EntityOperationError(String message, Throwable cause) {
        super(ACCOUNT_TRANSACTION_ERROR, message, cause);
    }
}
