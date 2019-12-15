package rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import rest.model.ErrorMessage;

@Provider
public class NoDataAvailableExceptionMapper implements ExceptionMapper<NoDataAvailableException>{

	@Override
	public Response toResponse(NoDataAvailableException ex) {
		ErrorMessage errorMessage= new ErrorMessage(ex.getMessage(), 404,"no documentation");
		return Response.status(Status.NOT_FOUND).
				entity(errorMessage).
				build();
	}

}
