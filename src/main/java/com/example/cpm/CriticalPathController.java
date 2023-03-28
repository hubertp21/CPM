package com.example.cpm;

import com.example.cpm.algorithm.PredecessorEventsExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriticalPathController {

    private final PredecessorEventsExecutor executor = new PredecessorEventsExecutor();

    @GetMapping("/criticalPath")
    public String criticalPath(@RequestParam String FILE) {
        String CONTENT_ROOT = "src/main/resources/static/";
        return executor.execute(CONTENT_ROOT+FILE);
    }
}
