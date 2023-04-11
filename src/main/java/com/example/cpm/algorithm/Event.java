package com.example.cpm.algorithm;

import static java.util.Collections.emptyList;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    private Character eventCharacter;
    private List<Character> predecessor;
    private Integer duration, startTime;
    private EventType eventType;

    public void setEventAsStartingEvent() {
        this.setEventCharacter('A');
        this.setPredecessor(emptyList());
        this.setEventType(EventType.STARTING_EVENT);
        this.setStartTime(0);
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
