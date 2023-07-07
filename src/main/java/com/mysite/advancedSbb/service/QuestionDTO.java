package com.mysite.advancedSbb.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class QuestionDTO {

    private String subject;
    private String content;
}
