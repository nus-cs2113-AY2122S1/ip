package duke;

import duke.exceptions.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_DONE_WORD = "done";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String[] COMMAND_WORDS_LIST = {COMMAND_TODO_WORD, COMMAND_DEADLINE_WORD, COMMAND_EVENT_WORD, COMMAND_LIST_WORD, COMMAND_DONE_WORD, COMMAND_EXIT_WORD};
    private static final int MAX_TASKS_NUMBER = 100;
    private static final Scanner INPUT_COMMAND = new Scanner(System.in);

    public static void main(String[] args) {
        Task[] tasksList = new Task[MAX_TASKS_NUMBER];
        int taskCount = 0;
        showWelcomeMessage();
        executeProgram(tasksList, taskCount);
        exitProgram();
    }

    public static void executeProgram(Task[] tasksList, int taskCount) {
        String inputLine = INPUT_COMMAND.nextLine();
        ;
        while (!inputLine.equals(COMMAND_EXIT_WORD)) {
            try {
                taskCount = executeCommand(tasksList, taskCount, inputLine.trim());
                inputLine = INPUT_COMMAND.nextLine();
            } catch (IllegalCommand e) {
                System.out.println("  OOPS! I'm sorry, but I don't know what that means :(");
                System.out.println("  I can only execute these commands: ");
                for (int i = 1; i <= COMMAND_WORDS_LIST.length; i++) {
                    System.out.println("  " + i + ". " + COMMAND_WORDS_LIST[i - 1]);
                }
                inputLine = INPUT_COMMAND.nextLine();
            } catch (EmptyToDoDescription e) {
                System.out.println("  OOPS! The description of a todo cannot be empty!");
                System.out.println("  Please input again with the correct format!");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (IllegalDeadlineInput e) {
                System.out.println("  OOPS! Please input again using this format:!");
                System.out.println("  'deadline <description> /by <due date>'");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (IllegalEventInput e) {
                System.out.println("  OOPS! Please input again using this format:!");
                System.out.println("  'event <description> /at <event date>'");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (EmptyTaskNumber e) {
                System.out.println("  OOPS! Please specify the completed task number!");
                System.out.println("  Example: 'done 1'");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("  OOPS! Please put only integer as the task number!");
                System.out.println("  Example: 'done 1'");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (NullPointerException e) {
                System.out.println("  OOPS! You must have typed the wrong number. Please type in again correctly!");
                inputLine = INPUT_COMMAND.nextLine();
            }
        }
    }

    public static int executeCommand(Task[] tasksList, int taskCount, String inputLine) throws IllegalCommand, EmptyToDoDescription, IllegalDeadlineInput, IllegalEventInput, EmptyTaskNumber {
        String firstWord;
        if (inputLine.contains(" ")) {
            firstWord = inputLine.split(" ")[0];
        } else {
            firstWord = inputLine;
        }

        switch (firstWord) {
        case COMMAND_TODO_WORD:
            if (!inputLine.contains(" ")) {
                throw new EmptyToDoDescription();
            }
            taskCount = addToDo(tasksList, taskCount, inputLine);
            break;
        case COMMAND_DEADLINE_WORD:
            if (!inputLine.contains(" /by ")) {
                throw new IllegalDeadlineInput();
            }
            taskCount = addDeadline(tasksList, taskCount, inputLine);
            break;
        case COMMAND_EVENT_WORD:
            if (!inputLine.contains(" /at ")) {
                throw new IllegalEventInput();
            }
            taskCount = addEvent(tasksList, taskCount, inputLine);
            break;
        case COMMAND_LIST_WORD:
            printTasks(tasksList, taskCount);
            break;
        case COMMAND_DONE_WORD:
            if (!inputLine.contains(" ")) {
                throw new EmptyTaskNumber();
            }
            markAsDone(tasksList, inputLine);
            break;
        default:
            throw new IllegalCommand();
        }
        return taskCount;
    }

    public static void markAsDone(Task[] tasksList, String inputLine) {
        int taskCompletedIndex = Integer.parseInt(inputLine.split("done ")[1]) - 1;
        tasksList[taskCompletedIndex].setDone(true);
        System.out.print("  Congratulations! You have completed the task:");
        System.out.println(" " + tasksList[taskCompletedIndex]);
    }

    public static int addEvent(Task[] tasksList, int taskCount, String inputLine) {
        String description = inputLine.split("event ")[1].split(" /at ")[0];
        String date = inputLine.split(" /at ")[1];
        tasksList[taskCount] = new Event(description, date);
        System.out.println("  Ok, I've added this task:");
        System.out.println("  " + tasksList[taskCount]);
        taskCount++;
        System.out.printf("  Now you have %d tasks in the list. Anything else?\n", taskCount);
        return taskCount;
    }

    public static int addDeadline(Task[] tasksList, int taskCount, String inputLine) {
        String description = inputLine.split("deadline ")[1].split(" /by ")[0];
        String date = inputLine.split(" /by ")[1];
        tasksList[taskCount] = new Deadline(description, date);
        System.out.println("  Ok, I've added this task:");
        System.out.println("  " + tasksList[taskCount]);
        taskCount++;
        System.out.printf("  Now you have %d tasks in the list. Anything else?\n", taskCount);
        return taskCount;
    }

    public static int addToDo(Task[] tasksList, int taskCount, String inputLine) {
        tasksList[taskCount] = new ToDo(inputLine.split("todo ")[1]);
        System.out.println("  Ok, I've added this task:");
        System.out.println("  " + tasksList[taskCount]);
        taskCount++;
        System.out.printf("  Now you have %d tasks in the list. Anything else?\n", taskCount);
        return taskCount;
    }

    public static void printTasks(Task[] tasksList, int taskCount) {
        if (taskCount == 0) {
            System.out.println("  You haven't added any task yet.");
        } else {
            for (int i = 1; i < taskCount + 1; i++) {
                System.out.println("  " + i + "." + tasksList[i - 1]);
            }
        }
    }

    public static void exitProgram() {
        System.out.println("  Bye! Have a nice day and hope to see you again!");
        System.exit(0);
    }

    public static void showWelcomeMessage() {
        String logo = "▒▒▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n"
                + "▒▒▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█\n"
                + "▒▒▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄\n"
                + "▒▒▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█\n"
                + "▒▒█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█\n"
                + "▒▒▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█\n"
                + "▒▒▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█\n"
                + "▒▒▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█\n"
                + "▒▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█\n"
                + "▒▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("  ____________________________________________________________");
        System.out.println("           This is your chatbot assistant, Neko Duke! :)");
        System.out.println("                What can I do for you today?");
        System.out.println("  ____________________________________________________________\n");
    }
}
