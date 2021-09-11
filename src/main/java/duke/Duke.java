package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.exception.MissingArgumentException;

public class Duke {

    private static final String DIVIDER = "    ____________________________________________________________";
    private static List<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        loadSave();
        greet();

        while (true) {
            String command = scan.nextLine();
            HashMap<String, String> commandArgs = parseCommand(command);
            System.out.println(DIVIDER);
            try {
                switch (commandArgs.get("command").toLowerCase()) {
                case "list":
                    list();
                    break;
                case "done":
                    setTaskDone(Integer.parseInt(commandArgs.get("param")), true);
                    break;
                case "delete":
                    deleteTask(Integer.parseInt(commandArgs.get("param")));
                    break;
                case "todo":
                    addTodo(commandArgs.get("param"), true);
                    break;
                case "deadline":
                    addDeadline(commandArgs.get("param"), commandArgs.get("by"), true);
                    break;
                case "event":
                    addEvent(commandArgs.get("param"), commandArgs.get("at"), true);
                    break;
                case "bye":
                    exit = true;
                    bye();
                    break;
                default:
                    printUnknownCommand();
                    break;
                }
            } catch (MissingArgumentException e) {
                printWithIndent(e.getMessage(), 5);
            } catch (NumberFormatException e) {
                printWithIndent("☹ OOPS!!! Index must be a number.", 5);
            } catch (IndexOutOfBoundsException e) {
                String indexErrorMsg = String.format("☹ OOPS!!! Item of index %s does not exist in tasks.",
                        commandArgs.get("param"));
                printWithIndent(indexErrorMsg, 5);
            } finally {
                try {
                    saveTasks();
                } catch (IOException e) {

                }
            }

            System.out.println(DIVIDER);

            if (exit) {
                break;
            } else {
                System.out.println("");
            }
        }

        scan.close();
    }

    public static HashMap<String, String> parseCommand(String command) {
        String[] parts = command.split(" /");
        HashMap<String, String> map = new HashMap<String, String>();
        String[] commandParts = parts[0].split(" ", 2);
        map.put("command", commandParts[0]);
        map.put("param", commandParts.length > 1 ? commandParts[1] : "");
        for (int i = 1; i < parts.length; i++) {
            String[] argParts = parts[i].split(" ", 2);
            if (argParts.length == 2) {
                map.put(argParts[0], argParts[1]);
            }
        }
        return map;
    }

    public static void printWithIndent(String text, int count) {
        System.out.println(" ".repeat(count) + text);
    }

    public static void greet() {
        System.out.println(DIVIDER);
        printWithIndent("Hello! I'm Duke", 5);
        printWithIndent("What can I do for you?", 5);
        System.out.println(DIVIDER + "\n");
    }

    public static void bye() {
        printWithIndent("Bye. Hope to see you again soon!", 5);
    }

    public static void printUnknownCommand() {
        printWithIndent("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", 5);
    }

    public static void addTodo(String task, Boolean print) throws MissingArgumentException {
        if (task == "") {
            throw new MissingArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(task);
        tasks.add(todo);
        if (print) {
            addSuccess(todo);
        }
    }

    public static void addDeadline(String task, String by, Boolean print) throws MissingArgumentException {
        if (task == "") {
            throw new MissingArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (by == null || by.isEmpty()) {
            throw new MissingArgumentException("☹ OOPS!!! The date of a deadline cannot be empty.");
        }
        Deadline deadline = new Deadline(task, by);
        tasks.add(deadline);
        if (print) {
            addSuccess(deadline);
        }
    }

    public static void addEvent(String task, String at, Boolean print) throws MissingArgumentException {
        if (task == "") {
            throw new MissingArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (at == null || at.isEmpty()) {
            throw new MissingArgumentException("☹ OOPS!!! The date of an event cannot be empty.");
        }
        Event event = new Event(task, at);
        tasks.add(event);
        if (print) {
            addSuccess(event);
        }
    }

    public static void addSuccess(Task task) {
        printWithIndent("Got it. I've added this task: ", 5);
        printWithIndent(task.toString(), 7);
        String taskCount = String.format("Now you have %d task%s in the list.", tasks.size(),
                tasks.size() == 1 ? "" : "s");
        printWithIndent(taskCount, 5);
    }

    public static void list() {
        printWithIndent("Here are the tasks in your list:", 5);
        int index = 1;
        for (Task item : tasks) {
            printWithIndent(String.format("%d.%s", index, item), 5);
            index++;
        }
    }

    public static void setTaskDone(int index, Boolean print) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        if (print) {
            printWithIndent("Nice! I've marked this task as done: ", 5);
            printWithIndent(task.toString(), 7);
        }
    }

    public static void loadSave() {
        try {
            File saveFile = new File("src/main/java/duke/save.txt");
            Scanner s = new Scanner(saveFile);
            int index = 1;
            while (s.hasNext()) {
                String[] taskParts = s.nextLine().split(" \\| ");
                String command = taskParts[0];
                String desc = taskParts[1];
                Boolean isDone = Boolean.parseBoolean(taskParts[2]);
                try {
                    switch (command) {
                    case "todo":
                        addTodo(desc, false);
                        break;
                    case "deadline":
                        addDeadline(desc, taskParts[3], false);
                        break;
                    case "event":
                        addEvent(desc, taskParts[3], false);
                        break;
                    }
                } catch (MissingArgumentException e) {

                } finally {
                    if (isDone) {
                        setTaskDone(index, false);
                    }
                    index++;
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public static void saveTasks() throws IOException {
        File saveFile = new File("src/main/java/duke/save.txt");
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
        FileWriter writer = new FileWriter(saveFile);
        for (Task item : tasks) {
            writer.append(item.toSave() + System.lineSeparator());
        }
        writer.close();
    }

    public static void deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        printWithIndent("Noted. I've removed this task:", 5);
        printWithIndent(task.toString(), 7);
        String taskCount = String.format("Now you have %d task%s in the list.", tasks.size(),
                tasks.size() == 1 ? "" : "s");
        printWithIndent(taskCount, 5);
    }
}
