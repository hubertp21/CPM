package com.example.cpm.algorithm.implementation;

import com.example.cpm.algorithm.Event;
import com.example.cpm.algorithm.EventType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CpmEventValidatorTest {

    private final CpmEventValidator underTest = new CpmEventValidator();

    @Test
    void shouldValidateEventsIfDataIsCorrect() {
        // given
        List<Event> eventList = buildSimpleEventList();

        // when then
        assertDoesNotThrow(() -> underTest.validateEvents(eventList));
    }

    @Test
    @SneakyThrows
    void shouldNotValidateEventsIfDataIsNotCorrect() {
        // given
        String cause = "EVENTS ARE NOT IN ALPHABETICAL ORDER";
        List<Event> eventList = buildIncorrectEventList();

        // when
        Exception e = assertThrows(RuntimeException.class, () -> underTest.validateEvents(eventList));

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
