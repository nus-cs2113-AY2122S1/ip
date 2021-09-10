package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String ADD_TASK_MSG = "Got it. I've added this duke.task: ";
    private static final String ERROR_MSG = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String TODO_ERROR = "The description of a todo cannot be empty.";
    private static final String DEADLINE_ERROR = "The description of a deadline cannot be empty and must have a '/by'.";
    private static final String EVENT_ERROR = "The description of an event cannot be empty and must have a '/at'.";
    private static final String[] taskTypes = {"todo", "deadline", "event"};
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        showHelloGreeting();
        executeResponses();
        showByeGreeting();
    }

    private static void executeResponses() {
        Scanner in = new Scanner(System.in);
        String text;
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println(LINE);
            String[] words = text.split(" ");
            int numOfTasks = tasks.size();
            try {
                switch (words[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < numOfTasks; i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    break;
                case "done":
                    int taskNum = Integer.parseInt(words[words.length - 1]);
                    tasks.get(taskNum - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[x] " + tasks.get(taskNum - 1).getDescription());
                    break;
                case "todo":
                    addTodo(text);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "deadline":
                    addDeadline(text);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "event":
                    addEvent(text);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    showErrorMessage();
                    break;
                }
            } catch (DukeException error) {
                System.out.println("☹ OOPS!!! " + error.getMessage());
            }
            System.out.println(LINE);
            text = in.nextLine();
        }
    }

    private static void addTodo(String text) throws DukeException {
        if (text.length() <= taskTypes[0].length()) {
            throw new DukeException(TODO_ERROR);
        }
        String[] todoTaskInfo = extractInfo(text, "todo");
        Task newTodo = new Todo(todoTaskInfo[0]);
        tasks.add(newTodo);
        System.out.println(ADD_TASK_MSG);
    }

    private static void addDeadline(String text) throws DukeException {
        if (text.length() <= taskTypes[1].length()) {
            throw new DukeException(DEADLINE_ERROR);
        }

        if (!text.contains("/by")) {
            throw new DukeException(DEADLINE_ERROR);
        }
        String[] deadlineTaskInfo = extractInfo(text, "deadline");
        Task newDeadline = new Deadline(deadlineTaskInfo[0], deadlineTaskInfo[1]);
        tasks.add(newDeadline);
        System.out.println(ADD_TASK_MSG);
    }

    private static void addEvent(String text) throws DukeException {
        if (text.length() <= taskTypes[2].length()) {
            throw new DukeException(EVENT_ERROR);
        }

        if (!text.contains("/at")) {
            throw new DukeException(EVENT_ERROR);
        }
        String[] eventTaskInfo = extractInfo(text, "event");
        Task newEvent = new Event(eventTaskInfo[0], eventTaskInfo[1]);
        tasks.add(newEvent);
        System.out.println(ADD_TASK_MSG);
    }

    private static void showByeGreeting() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void showHelloGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println(" Hello! I'm duke.Duke\n" +
                " What can I do for you?");
        System.out.println(LINE);
    }

    private static String[] extractInfo(String taskString, String taskType) {
        String[] taskInfo = new String[2];
        int slashPos = taskString.indexOf('/');
        switch (taskType) {
        case "todo":
            taskInfo[0] = taskString.substring(5);
            break;
        case "deadline":
            taskInfo[0] = taskString.substring(9, slashPos - 1);
            taskInfo[1] = taskString.substring(slashPos + 4);
            break;
        case "event":
            taskInfo[0] = taskString.substring(6, slashPos - 1);
            taskInfo[1] = taskString.substring(slashPos + 4);
            break;
        default:
            break;
        }
        return taskInfo;
    }

    private static void showErrorMessage() throws DukeException {
        throw new DukeException(ERROR_MSG);
    }
}
