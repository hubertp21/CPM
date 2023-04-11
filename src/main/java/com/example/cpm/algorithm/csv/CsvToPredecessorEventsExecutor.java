package com.example.cpm.algorithm.csv;

import com.example.cpm.algorithm.Event;
import com.example.cpm.algorithm.EventType;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CsvToPredecessorEventsExecutor {

    private final CsvValidator validator = new CsvValidator();
    private List<String[]> data;

    public List<Event> parseCsv(String file) {
        log.debug("Starting to parse csv file");
        validator.validateFile(file);
        if (!validator.fileIsValidated) {
            log.error("ERROR: {}", validator.getErrorCause());
            throw new RuntimeException(validator.getErrorCause());
        }
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            this.data = reader.readAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        validator.validateData(data);
        if (!validator.dataIsValidated) {
            log.error("ERROR: {}", validator.getErrorCause());
            throw new RuntimeException(validator.getErrorCause());
        }
        return convertDataToEventList(data);
    }

    private List<Event> convertDataToEventList(List<String[]> data) {
        return data.stream().map(this::convertRowToEvent).toList();
    }

    private Event convertRowToEvent(String[] row) {
        List<String> rowAsList = Arrays.stream(row).toList();
        Character eventChar = rowAsList.get(0).charAt(0);
        Integer duration = Integer.parseInt(rowAsList.get(1));
        List<Character> predecessors = rowAsList.get(2).chars()
                .mapToObj(e->(char)e).toList();
        return new Event(eventChar, predecessors, duration, 0, EventType.BASIC_EVENT);
    }
}
