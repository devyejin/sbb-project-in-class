package com.mysite.advancedSbb.repository;

import com.mysite.advancedSbb.domain.Answer;
import com.mysite.advancedSbb.domain.Question;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AnswerRepositoryTest {

    @Autowired
    private  QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    //테스트용 데이터
    private Question question1;
    private Question question2;
    private Answer answer11;
    private  Answer answer12;
    private  Answer answer21;

    @BeforeEach
    void insert() {
        //질문 넣기...
//        question1 = Question.builder()
//                .subject("질문 제목")
//                .content("질문 내용 블라블라")
//                .createDate(LocalDateTime.now())
//                .build();
//
//        question2 = Question.builder()
//                .subject("질문 제목22222")
//                .content("질문 내용 블라블라22222")
//                .createDate(LocalDateTime.now())
//                .build();
//
//        this.questionRepository.save(question1);
//        this.questionRepository.save(question2);
//
//        //답변 넣기
//        answer11 = Answer.builder()
//                .question(question1)
//                .content("질문1번에 대한 1th 답변")
//                .createDate(LocalDateTime.now())
//                .build();
//
//        answer12 = Answer.builder()
//                .question(question1)
//                .content("질문1번에 대한 2th 답변")
//                .createDate(LocalDateTime.now())
//                .build();
//
//        answer21 = Answer.builder()
//                .question(question2)
//                .content("질문2번에 대한 1th 답변")
//                .createDate(LocalDateTime.now())
//                .build();
//
//        this.answerRepository.save(answer11);
//        this.answerRepository.save(answer12);
//        this.answerRepository.save(answer21);

    }


    @Test
    @DisplayName("답변 저장했을 때, 질문이랑 매칭여부")
    void findById() {
        //given
        question1 = Question.builder()
                .subject("질문 제목")
                .content("질문 내용 블라블라")
                .createDate(LocalDateTime.now())
                .build();

        question2 = Question.builder()
                .subject("질문 제목22222")
                .content("질문 내용 블라블라22222")
                .createDate(LocalDateTime.now())
                .build();

        this.questionRepository.save(question1);
        this.questionRepository.save(question2);

        //답변 넣기
        answer11 = Answer.builder()
                .question(question1)
                .content("질문1번에 대한 1th 답변")
                .createDate(LocalDateTime.now())
                .build();

        answer12 = Answer.builder()
                .question(question1)
                .content("질문1번에 대한 2th 답변")
                .createDate(LocalDateTime.now())
                .build();

        answer21 = Answer.builder()
                .question(question2)
                .content("질문2번에 대한 1th 답변")
                .createDate(LocalDateTime.now())
                .build();

        Answer saveAnsw1 = this.answerRepository.save(answer11);
        Answer saveAnsw2 = this.answerRepository.save(answer12);
        Answer saveAnsw3 = this.answerRepository.save(answer21);

        //when
        //11답변이 1번 질문이랑 매칭되나?
        Optional<Answer> findAnswerOpt = this.answerRepository.findById(saveAnsw1.getId());
        //우선 null 고려안하고
        Answer findAnswer = findAnswerOpt.get();

        //then
        Assertions.assertThat(findAnswer.getQuestion().getContent()).isEqualTo(answer11.getQuestion().getContent());

    }

    @BeforeEach
    void clear() {
        this.answerRepository.deleteAll();
    }


}