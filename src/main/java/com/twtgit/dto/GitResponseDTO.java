package com.twtgit.dto;

import com.twtgit.entity.GitProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GitResponseDTO {

    private Integer total_count;
    private Boolean incomplete_results;
    private List<GitProject> items = new ArrayList();

}
