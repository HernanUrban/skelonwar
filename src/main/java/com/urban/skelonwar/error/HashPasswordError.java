package com.urban.skelonwar.error;

/**
 * Created by hernan.urban on 6/13/2017.
 */
public class HashPasswordError extends SkelonwarError{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 4169072297612810716L;

	public HashPasswordError() {
    }

    public HashPasswordError(String message) {
        super(message);
    }

    public HashPasswordError(String message, Throwable cause) {
        super(message, cause);
    }
}
