package com.twtgit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GitProject {

    String name;
    String full_name;
    String html_url;
    String description;

}
