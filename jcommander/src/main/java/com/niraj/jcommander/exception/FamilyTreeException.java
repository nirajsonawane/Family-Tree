package com.niraj.jcommander.exception;

public class FamilyTreeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FamilyTreeException() {
		super();

	}

	public FamilyTreeException(String message) {
		super(message);

	}

	public FamilyTreeException(String message, Throwable t) {
		super(message, t);
	}

}
