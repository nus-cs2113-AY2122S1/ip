package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;



public class Duke {

    public static void main(String[] args) {
        Ui.greetUser();
        TaskManager.engageUser();
        Ui.byeUser();
    }
}
