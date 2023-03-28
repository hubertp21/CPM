package com.example.cpm.algorithm.implementation;

import com.example.cpm.algorithm.Event;
import com.example.cpm.algorithm.EventType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CpmAlgorithmExecutorTest {

    private final CpmAlgorithmExecutor underTest = new CpmAlgorithmExecutor();
    private final CpmCriticalPathFinder cpmCriticalPathFinder = mock(CpmCriticalPathFinder.class);
    private final CpmEventValidator cpmEventValidator = mock(CpmEventValidator.class);

    @Test
    void shouldCalculateCriticalPathIfEventsAreValidated() {
        // given
        List<Event> eventList = buildSimpleEventList();

        // when
        var result = underTest.execute(eventList);

        // then
        assertFalse(result.isEmpty());
        assertEquals("[A, B, C]12", result);
    }

    @Test
    @SneakyThrows
    void shouldNotCalculateCriticalPathIfEventsAreNotValidated() {
        // given
        String cause = "EVENTS ARE NOT IN ALPHABETICAL ORDER";
        List<Event> eventList = buildIncorrectEventList();

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.execute(eventList));

        // then
        assertEquals(cause, e.getMessage());
    }

    private List<Event> buildSimpleEventList() {
        return List.of(new Event('A', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('B', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('C', List.of('A'), 4, EventType.BASIC_EVENT));
    }

    private List<Event> buildIncorrectEventList() {
        return List.of(new Event('A', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('D', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('C', List.of('A'), 4, EventType.BASIC_EVENT));
    }
}
