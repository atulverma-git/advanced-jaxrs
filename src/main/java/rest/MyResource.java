package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rest.exception.NoDataAvailableException;

@Path("/test")
public class MyResource {
	@PathParam("pathParam")
	String pathParam;
	
	@QueryParam("name")
	String queryParam;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod(){
		return pathParam+queryParam;
	}

	@GET
	@Path("/exception")
	@Produces(MediaType.APPLICATION_JSON)
	public String testException(){
			throw new NoDataAvailableException("No Data Available");
	}
}
