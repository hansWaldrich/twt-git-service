package com.twtgit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TwitterMessageModel {

    private TwitterUserModel user;
    private String text;
}
