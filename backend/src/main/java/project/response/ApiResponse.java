package project.response;

import java.time.LocalDateTime;

public class ApiResponse {
	private LocalDateTime timestamp;
	private String message;

	public ApiResponse() {
		super();
	}

	public ApiResponse(String message) {
		super();
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
