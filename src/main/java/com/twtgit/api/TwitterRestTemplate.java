package com.twtgit.api;

import com.twtgit.dto.TwitterResponseDTO;
import com.twtgit.entity.TwitterMessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * This component is responsible for Twitter API communication
 * and anything related.
 */
@Component
public class TwitterRestTemplate extends ApiRestTemplate {

    @Value("${api.twitter.url}")
	private String urlBase;
    @Value("${api.twitter.criteria}")
	private String criteria;
    @Value("${api.twitter.pageMax}")
	private String pageMax;
    @Value("${api.twitter.token}")
	private String token;
    @Value("${api.twitter.includeRT}")
	private boolean includeRT;

    public TwitterRestTemplate() {
    }

    public TwitterRestTemplate(String urlBase, String criteria, String pageMax, RestTemplate restTemplate) {
        super();
        this.urlBase = urlBase;
        this.criteria = criteria;
        this.pageMax = pageMax;
        this.restTemplate = restTemplate;
    }

    public List<TwitterMessageModel> callApiTwitter() throws Exception {
        return callTwitterService();
    }

    public List<TwitterMessageModel> callApiTwitter(String criteria) throws Exception {

        this.criteria = criteria != null && !criteria.isEmpty() ? criteria : this.criteria;

        return callTwitterService();
    }

    private List<TwitterMessageModel> callTwitterService() throws Exception {
        setToken(this.token);

        StringBuilder query = new StringBuilder("q=");
        query.append(this.criteria);
        if (!this.includeRT)
            query.append(" -RT");

        UriComponents builder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(this.urlBase)
                .query(query.toString())
                .queryParam("count", this.pageMax)
                .build(false);

        ResponseEntity<TwitterResponseDTO> response = this.restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                this.getHttpEntity(),
                TwitterResponseDTO.class
        );

        return response.getBody() != null ? response.getBody().getStatuses() : new ArrayList<>();
    }

}
