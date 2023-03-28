package com.example.cpm.algorithm;

import com.example.cpm.algorithm.csv.CsvToPredecessorEventsExecutor;
import com.example.cpm.algorithm.implementation.CpmAlgorithmExecutor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PredecessorEventExecutorTest {

    private final PredecessorEventsExecutor underTest = new PredecessorEventsExecutor();
    private final CsvToPredecessorEventsExecutor csvToPredecessorEventsExecutor = mock(CsvToPredecessorEventsExecutor.class);
    private final CpmAlgorithmExecutor cpmAlgorithmExecutor = mock(CpmAlgorithmExecutor.class);

    @Test
    void algorithmShouldExecuteIfEverythingValidates() {
        // given
        String file = "src/test/resources/sample_file.csv";

        // when
        var result = underTest.execute(file);

        // then
        assertFalse(result.isEmpty());
    }

}
