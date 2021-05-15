package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserRequest {

	@JsonProperty
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

  @Override
  public String toString() {
    return "CreateUserRequest{" +
      "username='" + username + '\'' +
      '}';
  }
}
