package com.example.cpm.controllers;

import com.example.cpm.algorithm.PredecessorEventsExecutor;
import com.example.cpm.algorithm.Wrapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {
    private final PredecessorEventsExecutor executor = new PredecessorEventsExecutor();

    @GetMapping("/")
    public String Menu(Model model) {
        model.addAttribute("text", "text");
        return "index";
    }

    @GetMapping("/path")
    public String GetPath(@RequestParam String path, Model answer) {
        String CONTENT_ROOT = "src/main/resources/static/";
        Wrapper data = executor.execute(CONTENT_ROOT+path);
        answer.addAttribute("items", data.getItems());
        answer.addAttribute("critical", data.getCriticalPath());
        return "result";
    }
}
