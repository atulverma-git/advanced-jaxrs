package rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import messenger.model.Message;

public class RestApiClient {
	public static void main(String[] arg){
		String baseUri = "http://localhost:8080/advanced-jaxrs/webapi/";
		
		/*create client using ClientBuilder assign to Client Interface
		 * Client should be one per application
		 */
		Client client = ClientBuilder.newClient();
		
		//Elegant way of calling end point
		WebTarget baseTarget = client.target(baseUri);
		WebTarget messageTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messageTarget.path("{messageId}"); // set messageId to get resolved later
		
		// build request to target uri and request get method.
		/*Response response =  client.target(uri).
							request(MediaType.APPLICATION_JSON).		// set MediaType in which response will be fetched
							get();				*/						// Class type can also be passed as argument.
		
		
		//-------------------------------------------- Get Request ---------------------------------------//
		
		Message message1 = singleMessageTarget.resolveTemplate("messageId", "1").
						request(MediaType.APPLICATION_JSON).
						get(Message.class);
		
		Message message2 = singleMessageTarget.resolveTemplate("messageId", "2").
				request(MediaType.APPLICATION_JSON).
				get(Message.class);
		
		// extract Message instance from wrapper of Response.
		//Message message = response.readEntity(Message.class);
		
		System.out.println(message1.getMessage());
		System.out.println(message2.getMessage());
		
		//-------------------------------------------- Post Request ---------------------------------------//
		Message message = new Message(4, "New Message from JX-RS Client", "averma");
		
		// wrap message object around a wrapper to get Entity
		Entity<Message> entity = Entity.json(message);
		
		Response postResponse = messageTarget.request().post(entity);
		
		System.out.println(postResponse.readEntity(Message.class).getMessage());
		
	}
}
