package com.example.cpm.algorithm.implementation;

import com.example.cpm.algorithm.Event;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CpmAlgorithmExecutor {

    private final CpmCriticalPathFinder cpmCriticalPathFinder = new CpmCriticalPathFinder();
    private final CpmEventValidator validator = new CpmEventValidator();

    public String execute(List<Event> eventList) {
        log.debug("Starting to validate event data");
        validator.validateEvents(eventList);
        if (!validator.isValidated) {
            log.error("ERROR: {}", validator.getErrorCause());
            throw new RuntimeException(validator.getErrorCause());
        }
        return cpmCriticalPathFinder.calculateCriticalPath(eventList);
    }
}
