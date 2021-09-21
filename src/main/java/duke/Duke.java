package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

//    protected static int taskCount = 0; //todo how to do away with taskCount and taskCompleted?
//    protected static int taskCompleted = 0;
//    protected static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Ui.greetUser();
        engageUser();
        Ui.byeUser();
    }

    /**
     * Engages user base on what the user has typed and executes a corresponding command.
     */
    public static void engageUser() {
        Scanner text = new Scanner(System.in);
        String taskType;
        String taskName;
        String taskDetails = "";

        String userInput;
        String[] words = new String[0];
        boolean isExit = false;

        do {
            taskType = text.next().toLowerCase();

            switch (taskType) {
            case "bye":
                isExit = true;
                break;
            case "hello":
            case "hi":
            case "yo":
                Ui.mockUser();
                break;
            case "list":
                Ui.printList(TaskManager.tasks);
                break;
            case "done":
            case "delete":
                userInput = text.nextLine();
                if (userInput.equals("")) {
                    Ui.printMissingTextError();
                } else {
                    words = userInput.split(" ");
                    userInput = words[1];
                    if (taskType.equals("done")) {
                        TaskManager.doneTask(userInput);
                    } else if (taskType.equals("delete")) {
                        TaskManager.deleteTask(userInput);
                    }

                }
                break;
            case "todo":
                taskName = text.nextLine();
                TaskManager.addTask(taskName, taskType, taskDetails);
                break;
            case "deadline":
            case "event":
                userInput = text.nextLine();

                if (taskType.equals("deadline")) {
                    words = userInput.split(" /by ");
                } else if (taskType.equals("event")) {
                    words = userInput.split(" /at ");
                }

                taskName = words[0];
                taskDetails = words[1];
                TaskManager.addTask(taskName, taskType, taskDetails);
                break;
            default:
                userInput = text.nextLine();
                Ui.printWrongTaskTypeError(taskType, userInput);
                break;
            }
        } while (!isExit);
    }

}
