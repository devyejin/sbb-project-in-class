package com.mysite.advancedSbb.controller;

import com.mysite.advancedSbb.domain.Question;
import com.mysite.advancedSbb.service.QuestionDTO;
import com.mysite.advancedSbb.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String questionlist(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question/list";
    }

    @GetMapping("/create")
    public String questionCreate() {

        return "question/form";
    }

    @PostMapping("/create")
    public String questionCreate(@RequestParam String subject, @RequestParam String content) {
        QuestionDTO questionDTO = QuestionDTO.builder()
                .content(content)
                .subject(subject)
                .build();
        this.questionService.register(questionDTO);
        return "redirect:/question/list";
    }

    @GetMapping("/detail/{id}")
    public String questionDetail() {
        return "question/detail";
    }

}
