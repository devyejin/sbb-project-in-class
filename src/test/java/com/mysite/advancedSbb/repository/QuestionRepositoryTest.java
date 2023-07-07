package com.mysite.advancedSbb.repository; //테스트케이스때 다 불러오기때문에 사용 가능

import com.mysite.advancedSbb.domain.Question;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class QuestionRepositoryTest {

    @Autowired // 테스트케이스기때문에 ㅇㅋ
    private QuestionRepository questionRepository;

    //테스트용 데이터
    private Question question1;
    private Question question2;

    //<--- 반복적으로 넣는게 귀찮아서 before로 뺴려고했는데, scope때문에 안되네 ㅋㅋ
    @BeforeEach
    @DisplayName("insert dummy data")
    void save() {
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


    }


    @Test
    void saveQuestion() {

        //given
//        Question question = Question.builder()
//                .subject("질문 제목")
//                .content("질문 내용 블라블라")
//                .createDate(LocalDateTime.now())
//                .build();

        //when
        Question savedQuestion = this.questionRepository.save(question1);//저장

        Optional<Question> findQuestionOpt = this.questionRepository.findById(savedQuestion.getId());
        Question findQuestion = findQuestionOpt.get();
        
        //then
//        Assertions.assertThat(savedQuestion).isEqualTo(findQuestion); //객체로비교하면 DTO, Entity차이로 불일치
        assertThat(savedQuestion.getId()).isEqualTo(findQuestion.getId());
    }

    @Test
    void findAll() {
        //given
//        Question question1 = Question.builder()
//                .subject("질문 제목")
//                .content("질문 내용 블라블라")
//                .createDate(LocalDateTime.now())
//                .build();
//
//        Question question2 = Question.builder()
//                .subject("질문 제목22222")
//                .content("질문 내용 블라블라22222")
//                .createDate(LocalDateTime.now())
//                .build();

        //when
//        this.questionRepository.save(question1);
//        this.questionRepository.save(question2);

        List<Question> findQuestionList = this.questionRepository.findAll();


        //then
        assertThat(findQuestionList.size()).isEqualTo(2);
    }


    @Test
    void findBySubject() {

        log.info("question1={}", question1.getSubject());
        //when
        Question findQuestion = this.questionRepository.findBySubject(question1.getSubject());
        //then
        assertThat(findQuestion.getContent()).isEqualTo(question1.getContent());
    }

    @Test
    void findBySubjectAndContent() {
        //when
        Question findQuestion = this.questionRepository.findBySubjectAndContent(question1.getSubject(), question1.getContent());

        //then
        assertThat(findQuestion.getContent()).isEqualTo(question1.getContent()); //바보야...
    }

    @Test
    void findBySubjectLike() {
        //when
        List<Question> questionlist = this.questionRepository.findBySubjectLike("%제목%");

        //then
        assertThat(questionlist.size()).isEqualTo(2);
    }

    @Test
    void update() {

        //when
        Optional<Question> findQuestionOpt = this.questionRepository.findById(1L);
        if (!findQuestionOpt.isEmpty()) {
            Question question = findQuestionOpt.get();
            question.setContent("내용을 수정해봤어요 ㅎㅎㅎ 잘 들어갈런지?");

            this.questionRepository.save(question);

        } else {
            log.info("해당하는 질문을 찾지 못함");
        }

        //then
        Optional<Question> findQuestion = this.questionRepository.findById(1L);
        assertThat(findQuestion.get().getContent()).isEqualTo("내용을 수정해봤어요 ㅎㅎㅎ 잘 들어갈런지?");

    }

    @Test
    void delete() {
        //when
        this.questionRepository.delete(question1);
        
        //then
        List<Question> all = this.questionRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @AfterEach
    void clear() {
        this.questionRepository.deleteAll();
    }

}