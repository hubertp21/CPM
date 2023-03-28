package com.example.cpm.algorithm.implementation;

import com.example.cpm.algorithm.Event;
import com.example.cpm.algorithm.EventType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CpmCriticalPathFinderTest {

    private final CpmCriticalPathFinder underTest = new CpmCriticalPathFinder();

    @Test
    void shouldCalculateSimplePath() {
        // given
        List<Event> eventList = buildSimpleEventList();

        // when
        var result = underTest.calculateCriticalPath(eventList);

        // then
        assertFalse(result.isEmpty());
        assertEquals("[A, B, C]12", result);
    }

    @Test
    void shouldCalculatePathWithEventWithManyPredecessors() {
        // given
        List<Event> eventList = buildEventListWithManyPredecessors();

        // when
        var result = underTest.calculateCriticalPath(eventList);

        // then
        assertFalse(result.isEmpty());
        assertEquals("[A, B, D]13", result);
    }

    @Test
    void shouldCalculateComplicatedPathsWithManyEventsAndPredecessors() {
        // given
        List<Event> eventList = buildEventListWithManyEventsAndPredecessors();

        // when
        var result = underTest.calculateCriticalPath(eventList);

        // then
        assertFalse(result.isEmpty());
        assertEquals("[A, B, E, F]22", result);
    }

    private List<Event> buildSimpleEventList() {
        return List.of(new Event('A', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('B', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('C', List.of('A'), 4, EventType.BASIC_EVENT));
    }

    private List<Event> buildEventListWithManyPredecessors() {
        return List.of(new Event('A', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('B', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('C', List.of('A'), 3, EventType.BASIC_EVENT),
                new Event('D', List.of('B', 'C'), 5, EventType.BASIC_EVENT));
    }

    private List<Event> buildEventListWithManyEventsAndPredecessors() {
        return List.of(new Event('A', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('B', List.of('A'), 4, EventType.BASIC_EVENT),
                new Event('C', List.of('A'), 3, EventType.BASIC_EVENT),
                new Event('D', List.of('B', 'C'), 5, EventType.BASIC_EVENT),
                new Event('E', List.of('B'), 10, EventType.BASIC_EVENT),
                new Event('F', List.of('D', 'E'), 4, EventType.BASIC_EVENT));
    }
}
