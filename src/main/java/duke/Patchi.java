package duke;

import duke.exception.InvalidCommandException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingTimingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Patchi {
    static String patchiDataPath = "data/patchidata.txt";

    public static void main(String[] args) {
        File patchiData = new File("data/patchidata.txt");
        System.out.println("full path: " + patchiData.getAbsolutePath());
        System.out.println("file exists?: " + patchiData.exists());

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int nextTaskIndex = 0;

        System.out.println("─── .o * : *. ¤ .* : ¤ o. ───");
        System.out.println("Patchi: Hello! I'm Patchi the tasks fairy Œ(ˊᵕˋ)B\n " +
                "What can I do for you today? Œ(ˊVˋ)B");

        printTransition();
        String input = in.nextLine();

        while (input.equals("bye") == false) {
            if (input.equals("list") == true) {
                listTasks(tasks, nextTaskIndex);
            } else if (input.startsWith("done")) {
                markTaskAsDone(tasks, nextTaskIndex, input);
            } else { //add task OR invalid command
                try {
                    addTask(tasks, nextTaskIndex, input);
                    System.out.println("Patchi: Got it! I have added " + tasks[nextTaskIndex].toString() +
                            " to your task list! Œ(ˆOˆ)B");
                    nextTaskIndex++;
                    System.out.println("Patchi: You have " + nextTaskIndex + " tasks now" +
                            "... Too much work... Œ(ˊnˋ)B");
                } catch (InvalidCommandException e) {
                    System.out.println("Patchi: I'm sorry, I don't understand what that means... Œ(ˊnˋ)B");
                } catch (NullPointerException e) {
                    System.out.println("<Failed to add new task>");
                }
            }

            printTransition();
            input = in.nextLine();
        }

        System.out.println("Patchi: Bye! Hope to see you again soon! Œ(~ˊᵕˋ~)B");
        System.out.println("─── .o * : *. ¤ .* : ¤ o. ───");
    }

    private static void markTaskAsDone(Task[] tasks, int nextTaskIndex, String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if (nextTaskIndex > taskIndex) {
            tasks[taskIndex].setDone(true);
            System.out.println("Patchi: Good job! I've marked this task as done on your list. " +
                    "Time for a break? Œ(ˊwˋ)B");
        } else {
            System.out.println("Patchi: That task doesn't seem to exist...... Œ(ˊnˋ)B");
        }
    }

    private static void listTasks(Task[] tasks, int nextTaskIndex) {
        if (nextTaskIndex > 0) {
            System.out.println("Patchi: Here is the list of tasks you currently have! Work hard~ Œ(˙O˙)B");
            for (int i = 0; i < nextTaskIndex; i++) {
                System.out.println((i + 1) + ". " + tasks[i].toString());
            }
        } else {
            System.out.println("Patchi: You have no tasks for now! Go and relax~ Œ(ˊuˋ)B");
        }
    }

    private static void addTask(Task[] tasks, int nextTaskIndex, String input)
            throws InvalidCommandException {
        try {
            if (input.startsWith("todo")) {
                addTodo(tasks, nextTaskIndex, input);
            } else if (input.startsWith("deadline")) {
                addDeadline(tasks, nextTaskIndex, input);
            } else if (input.startsWith("event")) {
                addEvent(tasks, nextTaskIndex, input);
            } else {
                throw new InvalidCommandException();
            }

           

        } catch (MissingDescriptionException e) {
            System.out.println("Patchi: You need to add a task description... Œ(ˊnˋ)B");
        } catch (MissingTimingException e) {
            System.out.println("Patchi: You need to add a timing for this kind of task! Œ(ˊnˋ)B");
        } catch (InvalidCommandException e) {
            System.out.println("Patchi: I'm sorry, I don't understand...Did you format your command correctly? Œ(ˊnˋ)B");
        }
    }

    private static void addTodo(Task[] tasks, int nextTaskIndex, String input)
            throws MissingDescriptionException {
        String description;

        try {
            description = input.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }

        tasks[nextTaskIndex] = new Todo(description);
    }

    private static void addDeadline(Task[] tasks, int nextTaskIndex, String input)
            throws InvalidCommandException, MissingTimingException, MissingDescriptionException {
        int indexOfBy = input.indexOf("/by");
        String by;
        String description;

        if (indexOfBy == -1) {
            throw new InvalidCommandException();
        }

        try {
            description = input.substring(9, indexOfBy - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }

        try {
            by = input.substring(indexOfBy + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingTimingException();
        }

        tasks[nextTaskIndex] = new Deadline(description, by);
    }

    private static void addEvent(Task[] tasks, int nextTaskIndex, String input)
            throws InvalidCommandException, MissingDescriptionException, MissingTimingException {
        int indexOfAt = input.indexOf("/at");
        String at;
        String description;

        if (indexOfAt == -1) {
            throw new InvalidCommandException();
        }

        try {
            description = input.substring(6, indexOfAt - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }

        try {
            at = input.substring(indexOfAt + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingTimingException();
        }

        tasks[nextTaskIndex] = new Event(description, at);
    }

    public static void printTransition() {
        System.out.println("─── .o * : typing... : ¤ o. ───");
        System.out.print("Me: ");
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}