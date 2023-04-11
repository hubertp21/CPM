package com.example.cpm.algorithm.implementation;

import com.example.cpm.algorithm.Event;

import java.util.List;

public class CpmEventValidator {

    private String cause;
    protected boolean isValidated = true;

    public void validateEvents(List<Event> eventList) {
        List<Character> characterList = eventList.stream().map(Event::getEventCharacter).toList();
        List<Character> sortedList = characterList.stream().sorted().toList();
        if (!characterList.equals(sortedList)) {
            isValidated = false;
            cause = "EVENTS ARE NOT IN ALPHABETICAL ORDER";
            throw new RuntimeException(cause);
        }
    }

    public String getErrorCause() {
        return this.cause;
    }
}
