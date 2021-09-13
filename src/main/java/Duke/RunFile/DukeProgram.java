package Duke.RunFile;

import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import Duke.Exception.DukeException;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;

import java.awt.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class DukeProgram {
    //List of Special User Commands
    public static final String EXIT_STRING = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";

    //Other constants
    public static final int NUM_OF_TASKS = 100;
    public static final String LINE = "____________________________________________________________";
    public static final String EVENT_KEYWORD = " /at";
    public static final String DEADLINE_KEYWORD = " /by";
    public static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";

    //Collections to store all the tasks
    public static final ArrayList<Task> taskList = new ArrayList<>();

    public static void printList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println(LINE);
            System.out.println(" No Tasks here yet. Go include some tasks!");
            System.out.println(LINE);
            System.out.print(System.lineSeparator());
            return;
        }

        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void printDukeGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greeting the User
        System.out.println(LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidDoneInstruction(String inWord, int index) {
        if (!inWord.contains(" ")) {
            return false;
        }

        String[] commands = inWord.split(" ");
        if (commands.length != 2) {
            return false;
        }

        if(isNumeric(commands[1])) {
            int taskDoneIndex = Integer.parseInt(commands[1]);
            return taskDoneIndex > 0 && taskDoneIndex < index;
        }

        return false;
    }

    public static void printTaskDone(String inWord, int index, ArrayList<Task> taskList) throws DukeException {
        if (!isValidDoneInstruction(inWord, index)) {
            throw new DukeException();
        }

        String[] commands = inWord.split(" ");
        int taskDoneIndex = Integer.parseInt(commands[1]);
        taskList.get(taskDoneIndex - 1).markAsDone();
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskList.get(taskDoneIndex - 1).toString());
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void manageDoneInstruction(String inWord, int index, ArrayList<Task> taskList) {
        try {
            printTaskDone(inWord, index, taskList);
        } catch (DukeException invalidDoneException){
            DukeException.invalidDoneException();
        }
    }

    public static boolean checkValidEvent(String inWord) {
        if (!inWord.contains(" ")) {
            return false;
        }

        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = inWord.split("\\s+", 2);
        if (commands.length != 2 || !inWord.contains(EVENT_KEYWORD)) {
            return false;
        }

        String[] description = commands[1].split(EVENT_KEYWORD, 2);
        if (description.length != 2) {
            return false;
        }

        String descriptionDetails = description[0].trim();
        String descriptionAt = description[1].trim();
        return !descriptionDetails.isEmpty() && !descriptionAt.isEmpty();
    }

    public static void printEvent(String inWord, int index, ArrayList<Task> taskList) throws DukeException {
        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = inWord.split("\\s+", 2);

        if(!checkValidEvent(inWord)) {
            throw new DukeException();
        }

        String[] details = commands[1].split(EVENT_KEYWORD, 2);
        String description = details[0].trim();
        String at = details[1].trim();

        Event newItem = new Event(description, at);
        taskList.add(newItem);
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + (index) +" tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void manageEvent(String inWord, int index, ArrayList<Task> taskList) {
        try {
            printEvent(inWord, index, taskList);
        } catch (DukeException invalidEventException) {
            DukeException.invalidEventException();
        }
    }

    public static boolean checkValidTodo(String inWord) {
        if (!inWord.contains(" ")) {
            return false;
        }

        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = inWord.split("\\s+", 2);
        String details = commands[1];
        boolean isNonEmptyDetails = !details.isEmpty();
        return commands.length == 2 && isNonEmptyDetails;
    }

    public static void printTodo(String inWord, int index, ArrayList<Task> taskList) throws DukeException {
        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = inWord.split("\\s+", 2);


        if(!checkValidTodo(inWord)) {
            throw new DukeException();
        }

        String description = commands[1];
        Todo newItem = new Todo(description);
        taskList.add(newItem);
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void manageTodo(String inWord, int index, ArrayList<Task> taskList) {
        try {
            printTodo(inWord, index, taskList);
        } catch (DukeException emptyTodoException) {
            DukeException.emptyTodoException();
        }
    }

    public static boolean checkValidDeadline(String inWord) {
        if (!inWord.contains(" ")) {
            return false;
        }

        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = inWord.split("\\s+", 2);
        if (commands.length != 2 || !inWord.contains(DEADLINE_KEYWORD)) {
            return false;
        }

        String[] description = commands[1].split(DEADLINE_KEYWORD, 2);
        if (description.length != 2) {
            return false;
        }

        String descriptionDetails = description[0].trim();
        String descriptionBy = description[1].trim();
        return !descriptionDetails.isEmpty() && !descriptionBy.isEmpty();
    }

    public static void printDeadline(String inWord, int index, ArrayList<Task> taskList) throws DukeException {
        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = inWord.split("\\s+", 2);

        if(!checkValidDeadline(inWord)) {
            throw new DukeException();
        }

        String[] details = commands[1].split(DEADLINE_KEYWORD, 2);
        String description = details[0].trim();
        String by = details[1].trim();

        Deadline newItem = new Deadline(description, by);
        taskList.add(newItem);
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + (index) +" tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void manageDeadline(String inWord, int index, ArrayList<Task> taskList) {
        try {
            printDeadline(inWord, index, taskList);
        } catch (DukeException invalidDeadlineException) {
            DukeException.invalidDeadlineException();
        }
    }

    public static boolean isValidDeleteInstruction(String inWord, int index) {
        if (!inWord.contains(" ")) {
            return false;
        }

        String[] commands = inWord.split(" ");
        if (commands.length != 2) {
            return false;
        }

        if(isNumeric(commands[1])) {
            int taskDoneIndex = Integer.parseInt(commands[1]);
            return taskDoneIndex > 0 && taskDoneIndex < index;
        }
        return false;
    }

    public static void printDelete(String inWord, int index, ArrayList<Task> taskList) throws DukeException {
        if (!isValidDeleteInstruction(inWord, index)) {
            throw new DukeException();
        }

        String[] commands = inWord.split(" ");
        int taskDeleteIndex = Integer.parseInt(commands[1]);
        System.out.println(LINE);
        System.out.println(" Noted! I've removed this task:");
        System.out.println("   " + taskList.get(taskDeleteIndex - 1).toString());
        taskList.remove(taskDeleteIndex - 1);
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void manageDelete(String inWord, int index, ArrayList<Task> taskList) {
        try {
            printDelete(inWord, index, taskList);
        } catch (DukeException invalidDeleteException) {
            DukeException.invalidDeleteException();
        }
    }

    public static void generalDukeException() {
        System.out.println(LINE);
        System.out.println("Please input a valid command!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void executeUserInstruction(String inWord, int index) {
        //split inWord by the first whitespace(s) into 2 separate strings
        String[] instruction = inWord.split("\\s+", 2);
        String instructionType = instruction[0];

        switch(instructionType) {
        case LIST_COMMAND:
            printList(taskList);
            break;
        case DONE_COMMAND:
            manageDoneInstruction(inWord, index, taskList);
            break;
        case EVENT_COMMAND:
            manageEvent(inWord, index, taskList);
            break;
        case TODO_COMMAND:
            manageTodo(inWord, index, taskList);
            break;
        case DEADLINE_COMMAND:
            manageDeadline(inWord, index, taskList);
            break;
        case DELETE_COMMAND:
            manageDelete(inWord, index, taskList);
            break;
        default:
            generalDukeException();
            break;
        }
    }

    //fix bug in update index
    public static int updateIndex(int index, String inWord) {
        //split inWord by the first whitespace(s) into 2 separate strings
        String[] instruction = inWord.split("\\s+", 2);
        String instructionType = instruction[0];

        //update index only if instruction is valid
        switch(instructionType) {
        case TODO_COMMAND:
            if (checkValidTodo(inWord)) {
                return index + 1;
            }
        case DEADLINE_COMMAND:
            if (checkValidDeadline(inWord)) {
                return index + 1;
            }
        case EVENT_COMMAND:
            if (checkValidEvent(inWord)) {
                return index + 1;
            }
        case DELETE_COMMAND:
            if (isValidDeleteInstruction(inWord, index)) {
                return index - 1;
            }
        default:
            return index;
        }
    }

    public static void printDukeExit() {
        System.out.println(LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        printDukeGreet();

        String inWord;
        Scanner scan = new Scanner(System.in);
        System.out.println();
        inWord = scan.nextLine();


        int index = 1;

        while (!inWord.equalsIgnoreCase(EXIT_STRING)) {
            executeUserInstruction(inWord, index);
            index = updateIndex(index, inWord);
            inWord = scan.nextLine();
        }

        //Exits when user types "bye"
        printDukeExit();
    }
}