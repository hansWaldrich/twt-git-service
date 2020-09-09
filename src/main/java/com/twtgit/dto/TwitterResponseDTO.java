package com.twtgit.dto;

import com.twtgit.entity.TwitterMessageModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TwitterResponseDTO {

    private List<TwitterMessageModel> statuses = new ArrayList<>();
}
