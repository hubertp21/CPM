package com.example.cpm.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Wrapper {

    private List<Event> items;
    private String criticalPath;
}
