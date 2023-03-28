package com.example.cpm.algorithm;

import com.example.cpm.algorithm.csv.CsvToPredecessorEventsExecutor;
import com.example.cpm.algorithm.implementation.CpmAlgorithmExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class PredecessorEventsExecutor {

    private final CsvToPredecessorEventsExecutor csvParser = new CsvToPredecessorEventsExecutor();
    private final CpmAlgorithmExecutor cpmAlgorithmExecutor = new CpmAlgorithmExecutor();
    private String result;

    public String execute(String FILE) {
        log.debug("Algorithm started to execute...");
        List<Event> eventList = Collections.emptyList();
        try {
            log.debug("Parsing csv...");
            eventList = csvParser.parseCsv(FILE);
        }
        catch (Exception e) {
            log.error("Error during file parsing");
            e.printStackTrace();
        }
        try {
            log.debug("Executing cpm algorithm");
            result = cpmAlgorithmExecutor.execute(eventList);
        }
        catch (Exception e) {
            log.error("Error while calculating critical path");
        }
        return result;
    }
}
