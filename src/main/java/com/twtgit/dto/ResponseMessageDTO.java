package com.twtgit.dto;

import com.twtgit.entity.GitProject;
import com.twtgit.entity.TwitterMessageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessageDTO {

    private GitProject gitProject;
    private List<TwitterMessageModel> tweets;


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Project : ");
        str.append(gitProject.getName());
        str.append("\n");
        str.append("Project URL : ");
        str.append(gitProject.getHtml_url());
        str.append("\n");
        str.append("Description : ");
        str.append(gitProject.getDescription());
        str.append("\n\n");
        str.append("Founded Tweets : ");
        getTweets().forEach(tmm -> {
            str.append("User : ");
            str.append(tmm.getUser());
            str.append("\n");
            str.append("Tweet : \n");
            str.append(tmm.getText());
            str.append("\n\n");
        });

        return str.toString();
    }
}
