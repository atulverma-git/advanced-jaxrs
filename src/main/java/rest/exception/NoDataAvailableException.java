package rest.exception;

public class NoDataAvailableException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NoDataAvailableException(String errorMessage){
		System.out.println(errorMessage);
	}
	
}
