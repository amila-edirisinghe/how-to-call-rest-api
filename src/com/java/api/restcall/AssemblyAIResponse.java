package com.java.api.restcall;

public class AssemblyAIResponse {

	private String id;
	private String status;
	private String text;
	private String error;
	private String audio_url;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAudio_url() {
		return audio_url;
	}

	public void setAudio_url(String audio_url) {
		this.audio_url = audio_url;
	}
}
