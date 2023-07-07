package com.mysite.advancedSbb.service;

import com.mysite.advancedSbb.domain.Question;
import com.mysite.advancedSbb.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;

    @Override
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    @Override
    public Question get(Long id) {
        return null;
    }

    @Override
    public boolean modify(Long id) {
        return false;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public void register(Question question) {

    }
}
