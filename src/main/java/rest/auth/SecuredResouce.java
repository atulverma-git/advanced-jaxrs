package rest.auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("secured")
public class SecuredResouce {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("message")
	public String secredMethod(){
		return "Authenticaiton Sucsessfull";
	}
}
