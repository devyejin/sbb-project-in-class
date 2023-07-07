package com.mysite.advancedSbb.repository;

import com.mysite.advancedSbb.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> { //습관적으로 Integer로 잡고 Repository에서 삽질 ㅠㅠㅠ

    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject); //규칙에의해 메서드명에 like 쓰면 쿼리에 반영됨

}
