package com.example.questionnaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    /**
     * メニュー画面
     */
    @GetMapping("/")
    public String menu() {
        return "menu";
    }

    /**
     * アンケート作成画面
     */
    @GetMapping("/create")
    public String create() {
        return "create";
    }

    /**
     * アンケート回答画面
     */
    @GetMapping("/answer")
    public String answer() {
        return "answer";
    }

    /**
     * 結果画面
     */
    @GetMapping("/result")
    public String result() {
        return "result";
    }

}