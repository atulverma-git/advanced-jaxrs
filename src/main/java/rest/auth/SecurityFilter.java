package rest.auth;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter{
	
	private static final String AUTHORIZATION_KEY = "Authorization";
	private static final String AUTHORIZATION_PRIFIX = "Basic";
	private static final String SECURED_PREFIX = "secured";

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		if(request.getUriInfo().getPath().contains(SECURED_PREFIX)){
			List<String> authHeader= request.getHeaders().get(AUTHORIZATION_KEY);
			if(authHeader!=null && authHeader.size()>0){
				String authToken = authHeader.get(0).replaceFirst(AUTHORIZATION_PRIFIX, "").trim();
				String decodeString = Base64.decodeAsString(authToken);
				String[] tokens = decodeString.split(":");
				//StringTokenizer tokens = new StringTokenizer(decodeString, ":");
				String userName = tokens[0];
				String password = tokens[1];
				System.out.println("user:"+userName+" password:"+password);
				if(userName.equals("user") && password.equals("password")){
					return;
				}
			}
			Response response = Response.status(Status.UNAUTHORIZED).
					entity("User doest not have access to this resource").
					build();
			request.abortWith(response);
		}
	}

}
