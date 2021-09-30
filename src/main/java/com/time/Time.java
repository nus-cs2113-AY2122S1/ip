package com.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Time {
    String time;

    public Time(String line) {
        this.time = interpret(line);
    }

    public String interpret(String line) {
        String time;
        line = line.trim();

        try{
            LocalDate d1 = LocalDate.parse(line);
            time = d1.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }catch (DateTimeParseException e) {
            time= line;
        }

        return time;
    }

    public String toString(){
        return time;
    }

}
