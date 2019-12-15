package rest.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieRestClient {

	public static void main(String[] args) {
		try{
			getMovieTitles("spiderman");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		

	}
	
	public static void getMovieTitles(String subStr) throws Exception{
		String location = "https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman";
		URL url = new URL(location);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader reader= new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String response = reader.readLine();
		StringBuilder responseBuilder = new StringBuilder();
		while(response!=null){
			responseBuilder.append(response);
			response = reader.readLine();
		}
		reader.close();
		
		String output = responseBuilder.toString();
		System.out.println(output);
		
		//List<String> movies = new ArrayList<>();
		
	}
}
