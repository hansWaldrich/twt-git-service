package com.twtgit.services;

import com.twtgit.api.GitRestTemplate;
import com.twtgit.api.TwitterRestTemplate;
import com.twtgit.dto.ResponseMessageDTO;
import com.twtgit.entity.GitProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwtGitService {

    @Autowired
    private GitRestTemplate gitRestTemplate;
    @Autowired
    private TwitterRestTemplate twitterRestTemplate;

    public TwtGitService() {

	}

	public ResponseEntity<List<ResponseMessageDTO>> getMatchConfiguredInfo(){
        HttpStatus status = HttpStatus.OK;
        List<ResponseMessageDTO> messages = new ArrayList<>();

        try{
            List<GitProject> projects = gitRestTemplate.callApiGit();

            messages = projects.stream()
                    .map(this::generateResponseMessageDTO)
                    .collect(Collectors.toList());
                if (messages.isEmpty())
                    status = HttpStatus.NOT_FOUND;

        } catch(Exception e) {
            status = HttpStatus.BAD_GATEWAY;
        }


        return ResponseEntity.status(status).body(messages);
    }

	public ResponseEntity<List<ResponseMessageDTO>> getMatchInfo(String classification){
        HttpStatus status = HttpStatus.OK;
        List<ResponseMessageDTO> messages = new ArrayList<>();

        try{
            List<GitProject> projects = gitRestTemplate.callApiGit(classification);

            messages = projects.stream()
                .map(this::generateResponseMessageDTO)
                .collect(Collectors.toList());

            if (messages.isEmpty())
                status = HttpStatus.NOT_FOUND;

        } catch(Exception e) {
            status = HttpStatus.BAD_GATEWAY;
        }


        return ResponseEntity.status(status).body(messages);
    }

    private ResponseMessageDTO generateResponseMessageDTO(GitProject prj) {
        String query = "'"+ prj.getName() + "','" + prj.getFull_name() + "'";
        ResponseMessageDTO response;

        try {
            response = new ResponseMessageDTO(prj, twitterRestTemplate.callApiTwitter(query));
        } catch (Exception e) {
            response = new ResponseMessageDTO(prj, new ArrayList<>());
        }

        return response;
    }
}
