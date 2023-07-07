package com.mysite.advancedSbb.service;

import com.mysite.advancedSbb.domain.Question;

import java.util.List;

public interface QuestionService {

    public List<Question> getList();
    public Question get(Long id);
    public boolean modify(Long id);
    public boolean remove(Long id);
    public void register(QuestionDTO questionDTO);
}
