package duke;

import java.util.ArrayList;

public class TaskList {

    // getting task names, deadline, duration etc should be done in Parser

    public static void addTodo(String input, ArrayList<Task> tasks) {
        String description = Parser.getTodoDescription(input);
        tasks.add(new Todo(description));
    }

    public static void addDeadline(String input, ArrayList<Task> tasks) {

    }

    public static void addEvent(String input, ArrayList<Task> tasks) {

    }
}
