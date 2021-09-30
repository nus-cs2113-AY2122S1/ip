package com.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent a Time object, in the form of "Sunday", or in the form of "1 Oct 2021"
 */
public class Time {
    String time;

    /**
     * Constructor of the Time, convert the String line into LocalDate if possible.
     *
     * @param line String object representing the time
     */
    public Time(String line) {
        this.time = interpret(line);
    }

    /**
     * Try to convert the String line into LocalDate if possible. If cannot, then keep the original format
     *
     * @param line String object representing the time
     */
    public String interpret(String line) {
        String time;
        line = line.trim();

        try {
            LocalDate d1 = LocalDate.parse(line);
            time = d1.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (DateTimeParseException e) {
            time = line;
        }

        return time;
    }

    /**
     * Overwrite
     */
    public String toString() {
        return time;
    }

}
