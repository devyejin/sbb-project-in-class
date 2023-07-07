package com.mysite.advancedSbb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@SequenceGenerator(
        name="ANSWER_SEQ_GENERATOR",
        sequenceName = "ANSWER_SEQ",
        initialValue = 1
)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                     , generator = "ANSWER_SEQ_GENERATOR")
    @Column(name="ANSWER_ID")
    private Long id;

    @Size(min = 10)
    @Column(columnDefinition = "TEXT") //columnDefinition 속성 : 길이제한 없음
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    //Q : A = 1 : N (연관관계주인)
    @ManyToOne
    @JoinColumn(name="QUESTION_ID")
    private Question question;

    public Answer() {}



}
