package com.twtgit.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class ApiRestTemplate {
    private String token;
    RestTemplate restTemplate = new RestTemplate();

    public ApiRestTemplate() {
    }

    protected HttpHeaders getHeader() {
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        if (this.token != null && !this.token.isEmpty())
            headers.add("Authorization", "Bearer " + this.token);
        return headers;
    }

    protected HttpEntity<String> getHttpEntity() {
    	return new HttpEntity<>(this.getHeader());
    }

    protected void setToken(String token){
        this.token = token;
    }
}
