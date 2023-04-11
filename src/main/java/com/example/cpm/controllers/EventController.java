package com.example.cpm.controllers;

import com.example.cpm.algorithm.csv.CsvToPredecessorEventsExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final CsvToPredecessorEventsExecutor executor = new CsvToPredecessorEventsExecutor();

    @GetMapping("/events")
    public String criticalPath(@RequestParam String FILE) {
        String CONTENT_ROOT = "src/main/resources/static/";
        return executor.parseCsv(CONTENT_ROOT+FILE).toString();
    }
}
