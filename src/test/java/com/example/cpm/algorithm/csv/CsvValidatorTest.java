package com.example.cpm.algorithm.csv;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CsvValidatorTest {

    private final CsvValidator underTest = new CsvValidator();

    @Test
    void shouldValidateFile() {
        // given
        String testFilePath = "abc.csv";

        // when then
        assertDoesNotThrow(() -> underTest.validateFile(testFilePath));
    }

    @Test
    @SneakyThrows
    void shouldNotValidateEmptyPath() {
        // given
        String cause = "FILE PATH IS EMPTY";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateFile(""));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldNotValidateFileWhichIsNotCsv() {
        // given
        String cause = "FILE EXTENSION DOES NOT MATCH CSV";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateFile("abc"));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    void shouldValidateCorrectData() {
        // given
        List<String[]> data = buildCorrectSampleData();

        // when
        underTest.validateData(data);

        // then
        assertDoesNotThrow(() -> underTest.validateData(data));
    }

    @Test
    @SneakyThrows
    void shouldNotValidateEmptyData() {
        // given
        String cause = "FILE IS EMPTY";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateData(
                Collections.emptyList()
        ));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldNotValidateDataIfEventIsNotPresent() {
        // given
        List<String[]> data = buildDataWithEmptyRow("EVENT");
        String cause = "NO EVENT PRESENT";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateData(
                data
        ));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldNotValidateDataIfDurationIsNotPresent() {
        // given
        List<String[]> data = buildDataWithEmptyRow("DURATION");
        String cause = "NO DURATION PRESENT";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateData(
                data
        ));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldNotValidateDataIfPredecessorsAreNotPresent() {
        // given
        List<String[]> data = buildDataWithEmptyRow("PREDECESSORS");
        String cause = "NO PREDECESSORS PRESENT";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateData(
                data
        ));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldNotValidateDataIfEventFormatIsIncorrect() {
        // given
        List<String[]> data = buildDataWIthIncorrectFormat("EVENT");
        String cause = "WRONG FORMAT OF EVENT";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateData(
                data
        ));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldNotValidateDataIfDurationFormatIsIncorrect() {
        // given
        List<String[]> data = buildDataWIthIncorrectFormat("DURATION");
        String cause = "WRONG FORMAT OF DURATION";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateData(
                data
        ));

        // then
        assertEquals(cause, e.getMessage());
    }

    @Test
    @SneakyThrows
    void shouldNotValidateDataIfPredecessorsFormatIsIncorrect() {
        // given
        List<String[]> data = buildDataWIthIncorrectFormat("PREDECESSORS");
        String cause = "WRONG FORMAT OF PREDECESSORS";

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateData(
                data
        ));

        // then
        assertEquals(cause, e.getMessage());
    }

    private List<String[]> buildCorrectSampleData() {
        String sampleEvent = "A";
        String sampleDuration = "10";
        String samplePredecessors = "CD";
        String[] strings = {sampleEvent, sampleDuration, samplePredecessors};
        return List.of(strings, strings);
    }

    private List<String[]> buildDataWithEmptyRow(String row) {
        if (Objects.equals(row, "EVENT")) {
            String sampleEvent = "";
            String sampleDuration = "10";
            String samplePredecessors = "CD";
            String[] strings = {sampleEvent, sampleDuration, samplePredecessors};
            return List.of(strings, strings);
        }
        else if (Objects.equals(row, "DURATION")) {
            String sampleEvent = "A";
            String sampleDuration = "";
            String samplePredecessors = "CD";
            String[] strings = {sampleEvent, sampleDuration, samplePredecessors};
            return List.of(strings, strings);
        }
        else if (Objects.equals(row, "PREDECESSORS")) {
            String sampleEvent = "A";
            String sampleDuration = "10";
            String samplePredecessors = "";
            String[] strings = {sampleEvent, sampleDuration, samplePredecessors};
            return List.of(strings, strings);
        }
        String sampleEvent = "";
        String sampleDuration = "";
        String samplePredecessors = "";
        String[] strings = {sampleEvent, sampleDuration, samplePredecessors};
        return List.of(strings, strings);
    }

    private List<String[]> buildDataWIthIncorrectFormat(String row) {
        if (Objects.equals(row, "EVENT")) {
            String sampleEvent = "z1";
            String sampleDuration = "10";
            String samplePredecessors = "CD";
            String[] strings = {sampleEvent, sampleDuration, samplePredecessors};
            return List.of(strings, strings);
        }
        else if (Objects.equals(row, "DURATION")) {
            String sampleEvent = "A";
            String sampleDuration = "aa";
            String samplePredecessors = "CD";
            String[] strings = {sampleEvent, sampleDuration, samplePredecessors};
            return List.of(strings, strings);
        }
        else if (Objects.equals(row, "PREDECESSORS")) {
            String sampleEvent = "A";
            String sampleDuration = "10";
            String samplePredecessors = "15";
            String[] strings = {sampleEvent, sampleDuration, samplePredecessors};
            return List.of(strings, strings);
        }
        String sampleEvent = "z1";
        String sampleDuration = "aa";
        String samplePredecessors = "15";
        String[] strings = {sampleEvent, sampleDuration, samplePredecessors};
        return List.of(strings, strings);
    }
}
