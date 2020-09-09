package com.twtgit.api;

import com.twtgit.dto.GitResponseDTO;
import com.twtgit.entity.GitProject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * This component is responsible for GIT API communication
 * and anything related.
 */
@Component
public class GitRestTemplate extends ApiRestTemplate {

    @Value("${api.git.url}")
	private String urlBase;
    @Value("${api.git.criteria}")
	private String criteria;
    @Value("${api.git.sort}")
	private String sort;
    @Value("${api.git.order}")
	private String order;
    @Value("${api.git.page}")
	private String page;
    @Value("${api.git.pageMax}")
	private String pageMax;

    public GitRestTemplate() {
    }

    public GitRestTemplate(String urlBase, String criteria, String sort, String order, String page, String pageMax, RestTemplate restTemplate) {
        super();
        this.urlBase = urlBase;
        this.criteria = criteria;
        this.sort = sort;
        this.order = order;
        this.page = page;
        this.pageMax = pageMax;
        this.restTemplate = restTemplate;
    }

    public List<GitProject> callApiGit() throws Exception {
        return callGitService();
    }

    public List<GitProject> callApiGit(String criteria) throws Exception {

        this.criteria = criteria != null && !criteria.isEmpty() ? criteria : this.criteria;

        return callGitService();
    }

    private List<GitProject> callGitService() throws Exception{
        UriComponents builder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(this.urlBase)
                .query("q="+this.criteria)
                .queryParam("sort", this.sort)
                .queryParam("order", this.order)
                .queryParam("page", this.page)
                .queryParam("per_page", this.pageMax)
                .build(false);

        ResponseEntity<GitResponseDTO> response = this.restTemplate.getForEntity(
                builder.toUriString(),
                GitResponseDTO.class
        );

        return response.getBody() != null ? response.getBody().getItems() : new ArrayList<>();
    }
}
