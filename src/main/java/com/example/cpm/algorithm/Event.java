package com.example.cpm.algorithm;

import static java.util.Collections.emptyList;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Event {

    private Character eventCharacter;
    private List<Character> predecessor;
    private Integer duration;
    private EventType eventType;

    public void setEventAsStartingEvent() {
        this.setEventCharacter('A');
        this.setPredecessor(emptyList());
        this.setEventType(EventType.STARTING_EVENT);
    }

    public void setEventAsEndingEvent(){
        this.setEventType(EventType.ENDING_EVENT);
    }

    public void setEventAsDummyEvent() {
        this.setEventType(EventType.DUMMY_EVENT);
    }

    @Override
    public String toString() {
        return eventCharacter + duration.toString() + predecessor.toString();
    }
}
