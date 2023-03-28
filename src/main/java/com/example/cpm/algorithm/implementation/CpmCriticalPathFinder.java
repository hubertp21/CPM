package com.example.cpm.algorithm.implementation;

import com.example.cpm.algorithm.Event;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CpmCriticalPathFinder {

    private final Set<Character> criticalPath = new HashSet<>();
    private Integer criticalPathCost = 0;
    
    public String calculateCriticalPath(List<Event> eventList) {
        criticalPath.clear();
        criticalPathCost = 0;
        setUpEvents(eventList);
        for (Event event : eventList) {
            criticalPath.add(event.getEventCharacter());
            if (event.getPredecessor().size() > 1) {
                List<Character> predecessors = event.getPredecessor();
                int maxDuration = 0;
                char lastPredecessor = '!';
                removeUnnecessaryEvents(eventList, predecessors, maxDuration, lastPredecessor);
            }
        }
        return criticalPath.toString() + calculateCost(eventList);
    }

    private void removeUnnecessaryEvents(List<Event> eventList, List<Character> predecessors, int maxDuration, char lastPredecessor) {
        for (Character predecessor : predecessors) {
            Event predecessorEvent = getEvent(eventList, predecessor);
            if (predecessorEvent.getDuration() > maxDuration) {
                maxDuration = predecessorEvent.getDuration();
                if (lastPredecessor != '!') {
                    criticalPath.remove(lastPredecessor);
                }
                lastPredecessor = predecessorEvent.getEventCharacter();
            }
            else {
                criticalPath.remove(predecessorEvent.getEventCharacter());
            }
        }
    }

    private Event getEvent(List<Event> eventList, Character event) {
        return eventList.stream()
                .filter(e -> event.equals(e.getEventCharacter()))
                .findAny()
                .orElse(null);
    }

    private void setUpEvents(List<Event> eventList) {
        eventList.get(0).setEventAsStartingEvent();
        eventList.get(eventList.size()-1).setEventAsEndingEvent();
        eventList.forEach(event -> {
            if (event.getDuration() == 0) {
                event.setEventAsDummyEvent();
            }
        });
    }

    private Integer calculateCost(List<Event> eventList) {
        for (Character event : criticalPath) {
            criticalPathCost += getEvent(eventList, event).getDuration();
        }
        return criticalPathCost;
    }
}
