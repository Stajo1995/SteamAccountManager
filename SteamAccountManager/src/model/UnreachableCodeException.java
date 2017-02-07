package model;

public class UnreachableCodeException extends Exception {

	private static final long serialVersionUID = -4858371044362312306L;

	public UnreachableCodeException() {

	}

	public UnreachableCodeException(String errorMessage) {
		super(errorMessage);
	}

}
