package com.twtgit.controllers;

import com.twtgit.dto.ResponseMessageDTO;
import com.twtgit.services.TwtGitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/search-info")
@CrossOrigin(origins = "*")
public class TwtGitController {

    @Autowired
    private TwtGitService twtGitService;

    @GetMapping(value = "/get-tweets")
    public ResponseEntity<List<ResponseMessageDTO>> getTweets(){
        return twtGitService.getMatchConfiguredInfo();
    }

    @GetMapping(value = "/get-tweets/{classification}")
    public ResponseEntity<List<ResponseMessageDTO>> getTweets(@PathVariable String classification){
        return twtGitService.getMatchInfo(classification);
    }
}
