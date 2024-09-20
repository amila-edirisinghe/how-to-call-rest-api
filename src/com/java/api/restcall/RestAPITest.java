package com.java.api.restcall;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class RestAPITest {

	public static void main(String[] args) throws Exception {
		
		AssemblyAIRequest request = new AssemblyAIRequest();
		request.setAudio_url(AssemblyAIConstent.AUDIO_URL);
		
		Gson jasonObject = new Gson();
		String jasonRequest =  jasonObject.toJson(request);
		
		System.out.println(jasonRequest);
		
		HttpRequest postRequest = HttpRequest.newBuilder() 
				.uri(new URI(AssemblyAIConstent.API_URL)) 
				.header(AssemblyAIConstent.AUTHORIZATION,AssemblyAIConstent.API_KEY) 
				.POST(BodyPublishers.ofString(jasonRequest))
				.build();
		
		HttpClient httpClient = HttpClient.newHttpClient();
		
		HttpResponse<String> httpPostResponse = httpClient.send(postRequest, BodyHandlers.ofString());
		
		AssemblyAIResponse assemblyAIResponse = jasonObject.fromJson(httpPostResponse.body(), AssemblyAIResponse.class);
		
		HttpRequest getRequest = HttpRequest.newBuilder() 
				.uri(new URI(AssemblyAIConstent.API_URL + "/" + assemblyAIResponse.getId())) 
				.header(AssemblyAIConstent.AUTHORIZATION,AssemblyAIConstent.API_KEY) 
				.GET()
				.build();
		
		while (true) {
			
			HttpResponse<String> httpGetResponse = httpClient.send(getRequest, BodyHandlers.ofString());
			AssemblyAIResponse assemblyAIGetResponse = jasonObject.fromJson(httpGetResponse.body(), AssemblyAIResponse.class);
			
			System.out.println(assemblyAIGetResponse.getId()); 
			System.out.println(assemblyAIGetResponse.getStatus()); 
			System.out.println(assemblyAIGetResponse.getError()); 
			Thread.sleep(1000);
			
			if ("completed".equals(assemblyAIGetResponse.getStatus()) || "error".equals(assemblyAIGetResponse.getStatus())) {
				break;
			}
			
			
			
		}
		System.out.println(assemblyAIResponse.getId());
		System.out.println(assemblyAIResponse.getStatus());
		
		
		
	}

}
