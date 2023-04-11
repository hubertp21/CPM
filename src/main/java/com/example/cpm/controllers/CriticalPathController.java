package com.example.cpm.controllers;

import com.example.cpm.algorithm.Event;
import com.example.cpm.algorithm.PredecessorEventsExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class CriticalPathController {

    private final PredecessorEventsExecutor executor = new PredecessorEventsExecutor();

    @GetMapping("/criticalPath")
    public String criticalPath(@RequestParam String FILE) {
        String CONTENT_ROOT = "src/main/resources/static/";
        List<Event> eventList = Collections.emptyList();
        return executor.execute(CONTENT_ROOT+FILE).getCriticalPath();
    }
}
