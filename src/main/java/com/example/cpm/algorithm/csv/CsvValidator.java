package com.example.cpm.algorithm.csv;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class CsvValidator {

    protected boolean fileIsValidated = true;
    protected boolean dataIsValidated = true;
    private String cause;

    public void validateFile(String file) {
        if (file.isEmpty()) {
            fileIsValidated = false;
            cause = "FILE PATH IS EMPTY";
            throw new RuntimeException(cause);
        }
        if (!file.endsWith(".csv")) {
            fileIsValidated = false;
            cause = "FILE EXTENSION DOES NOT MATCH CSV";
            throw new SecurityException(cause);
        }
    }

    public void validateData(List<String[]> data) {
        if (data.isEmpty()) {
            dataIsValidated = false;
            cause = "FILE IS EMPTY";
            throw new RuntimeException(cause);
        }
        data.forEach(this::validateRow);
    }

    public String getErrorCause() {
        return this.cause;
    }

    private void validateRow(String[] row) {
        List<String> rowAsList = Arrays.stream(row).toList();
        checkIfRowIsEmpty(rowAsList);
        checkIfDataIsCorrect(rowAsList);
    }

    private void checkIfRowIsEmpty(List<String> row) {
        if (row.get(0).isEmpty()) {
            dataIsValidated = false;
            cause = "NO EVENT PRESENT";
            throw new RuntimeException(cause);
        }
        if (row.get(1).isEmpty()) {
            dataIsValidated = false;
            cause = "NO DURATION PRESENT";
            throw new RuntimeException(cause);
        }
        if (row.get(2).isEmpty()) {
            dataIsValidated = false;
            cause = "NO PREDECESSORS PRESENT";
            throw new RuntimeException(cause);
        }
    }

    private void checkIfDataIsCorrect(List<String> row) {
        if (row.get(0).length() > 1 || ( row.get(0).charAt(0) <= 'A' && row.get(0).charAt(0) >= 'Z')) {
            dataIsValidated = false;
            cause = "WRONG FORMAT OF EVENT";
            throw new RuntimeException(cause);
        }
        if (!row.get(1).chars().allMatch(Character::isDigit)) {
            dataIsValidated = false;
            cause = "WRONG FORMAT OF DURATION";
            throw new RuntimeException(cause);
        }
        if (!row.get(2).chars().allMatch(Character::isLetter)) {
            dataIsValidated = false;
            cause = "WRONG FORMAT OF PREDECESSORS";
            throw new RuntimeException(cause);
        }
    }
}
