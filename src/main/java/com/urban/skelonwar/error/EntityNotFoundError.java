package com.urban.skelonwar.error;

import static com.urban.skelonwar.error.DefaultErrorCodes.ENTITY_NOT_FOUND;

/**
 * Created by hernan.urban on 5/24/2017.
 */
public class EntityNotFoundError extends SkelonwarError {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8388960980582258109L;

	public EntityNotFoundError() {
        super(ENTITY_NOT_FOUND, "Entity was not found.");
    }

    public EntityNotFoundError(String message) {
        super(ENTITY_NOT_FOUND, message);
    }

    public EntityNotFoundError(String message, Throwable cause) {
        super(ENTITY_NOT_FOUND, message, cause);
    }
}
