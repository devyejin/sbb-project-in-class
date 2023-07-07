package com.mysite.advancedSbb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Entity
@Getter
@Builder // setter를 지양하자 ( no silver bullet , 케바케 )
@SequenceGenerator(
        name="QUESTION_SEQ_GENERATOR",
        sequenceName = "QUESTION_SEQ",
        initialValue = 1
)
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE
                        , generator ="QUESTION_SEQ_GENERATOR") //시퀀스 전략 - DB 시퀀스 오브젝트 사용
    @Column(name="QUESTION_ID")
    private Long id;

    @Size(min = 5, max = 200) //@Column length 속성은 max값
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //Q -> A (삭제)전파 , 조회
    private List<Answer> answerList = new ArrayList<>();

    //조회용


    public Question() {}



}
