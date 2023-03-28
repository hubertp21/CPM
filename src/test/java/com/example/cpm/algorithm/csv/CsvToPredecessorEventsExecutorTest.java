package com.example.cpm.algorithm.csv;

import com.example.cpm.algorithm.Event;
import com.example.cpm.algorithm.EventType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CsvToPredecessorEventsExecutorTest {

    private final CsvToPredecessorEventsExecutor underTest = new CsvToPredecessorEventsExecutor();
    private final CsvValidator validator = mock(CsvValidator.class);

    @Test
    void shouldParseFileWhenDataIsValidated() {
        // given
        String file = "src/test/resources/sample_file.csv";

        // when
        var result = underTest.parseCsv(file);

        // then
        assertFalse(result.isEmpty());
        assertEquals(6, result.size());
        assertEquals(new Event('A', List.of('A'), 5, EventType.BASIC_EVENT), result.get(0));
        assertEquals(new Event('F', List.of('B', 'C'), 4, EventType.BASIC_EVENT), result.get(5));
    }

    @Test
    @SneakyThrows
    void shouldNotParseUnvalidatedFile() {
        // given
        String cause = "FILE PATH IS EMPTY";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.parseCsv(""));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldNotParseUnvalidatedData() {
        // given
        String dummyFile = "src/test/resources/incorrect_data_file.csv";
        String cause = "WRONG FORMAT OF EVENT";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.parseCsv(dummyFile));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldThrowGenericExceptionWhenFileIsEmpty() {
        // given
        String dummyFile = "src/test/resources/empty_file.csv";
        String cause = "Cannot invoke \"java.util.List.isEmpty()\" because \"data\" is null";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.parseCsv(dummyFile));

        // then
        assertEquals(cause, e.getMessage());
    }
}
