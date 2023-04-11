package com.example.cpm.algorithm.implementation;

import com.example.cpm.algorithm.Event;
import com.example.cpm.algorithm.Wrapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CpmCriticalPathFinder {

    private final Set<Character> criticalPath = new HashSet<>();
    
    public Wrapper calculateCriticalPath(List<Event> eventList) {
        Event longest = getStartingTimes(eventList);
        int criticalDuration = longest.getStartTime() + longest.getDuration();

        criticalPath.add(longest.getEventCharacter());
        while(true) {
            double max = 0;
            char predecessor = '!';
            if(longest.getPredecessor().get(0) == 'A') {
                criticalPath.add('A');
                break;
            }
            for (char pre : longest.getPredecessor()) {
                if(getEvent(eventList, pre).getStartTime() + getEvent(eventList, pre).getDuration() > max) {
                    max = getEvent(eventList, pre).getStartTime() + getEvent(eventList, pre).getDuration();
                    predecessor = pre;
                }
            }
            longest = getEvent(eventList, predecessor);
            criticalPath.add(predecessor);
        }

        return new Wrapper(eventList, criticalPath.toString() + criticalDuration);
    }

    public Event getStartingTimes(List<Event> eventList) {
        Event longest = new Event();
        int longestTime = 0;
        for(Event event : eventList) {
            if(event.getEventCharacter() == 'A')
                event.setStartTime(0);
            else {
                int max = 0;
                for(char predecessor : event.getPredecessor()) {
                    if(getEvent(eventList, predecessor).getStartTime() + getEvent(eventList, predecessor).getDuration() > max)
                        max = getEvent(eventList, predecessor).getStartTime() + getEvent(eventList, predecessor).getDuration();
                }
                event.setStartTime(max);
            }
            if(event.getDuration() + event.getStartTime() > longestTime) {
                longestTime = event.getDuration() + event.getStartTime();
                longest = event;
            }
        }

        return longest;
    }

    private Event getEvent(List<Event> eventList, Character event) {
        return eventList.stream()
                .filter(e -> event.equals(e.getEventCharacter()))
                .findAny()
                .orElse(null);
    }
}
