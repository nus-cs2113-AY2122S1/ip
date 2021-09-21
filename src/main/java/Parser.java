package ip.src.main.java;

import java.util.Objects;
import java.util.Scanner;

public class Parser {

    private static String line = null;
    private static String[] arr;
    private static String[] deadline;
    private static String[] event;

    public String input() {
        return line;
    }

    public String data() {
        return line.substring(1);
    }

    public String keyword() {
        arr = line.split(" ", 2);
        return arr[0];
    }

    public String listIndex() {
        arr = line.split(" ", 2);
        return arr[1];
    }

    public String splitDeadlineTask() {
        deadline = line.substring(1).split("/by");
        return deadline[0];
    }

    public String splitDeadlineBy() {
        deadline = line.substring(1).split("/by");
        return deadline[1];
    }

    public String splitEventTask() {
        event = line.substring(1).split("/at");
        return event[0];
    }

    public String splitEventBy() {
        event = line.substring(1).split("/at");
        return event[1];
    }

    public void feed(String input) {
        line = input;
    }

}



