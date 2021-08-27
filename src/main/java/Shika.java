import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

import shikabot.task.Task;
import shikabot.task.Todo;
import shikabot.task.Deadline;
import shikabot.task.Event;

import shikabot.exception.InvalidCommandException;
import shikabot.exception.TaskNegativeException;
import shikabot.exception.TaskNotFoundException;
import shikabot.exception.InvalidEventException;
import shikabot.exception.InvalidDeadlineException;

import shikabot.ui.TextUi;

import shikabot.saves.SaveFile;

public class Shika {

    public static ArrayList<Task> tasks;

    public TextUi ui;
    public SaveFile saveFile;

    public static void main(String[] args) {
        new Shika().run();
    }

    /**
     * Function that calls other functions to run Shika.
     */
    public void run() {
        try {
            setupShika();
        } catch (FileNotFoundException e) {
            System.out.println("I can't find the save file AHHHHHHH.\n");
        }
        runShika();
    }

    /**
     * Function that attempts to load tasks from ShikaTasks.txt. If the file or parent directories do not exist,
     * it creates them.
     * @throws FileNotFoundException when ShikaTasks.txt is not found.
     */
    public void setupShika() throws FileNotFoundException {
        this.ui = new TextUi();
        this.saveFile = new SaveFile("data/ShikaTasks.txt");
        ui.printLogo();
        ui.printWelcomeMessage(saveFile.setupSave());
        tasks = saveFile.loadTasks();
    }

    /**
     * Function that calls getCommand() in a loop to run Shika. Loop can be exited by inputting "bye".
     */
    public void runShika() {
        Scanner in = new Scanner(System.in);
        String text;
        while(in.hasNextLine()) {
            text = in.nextLine();
            if (text.trim().equals("bye")) {
                ui.printExitMessage();
                return;
            }
            try {
                getCommand(text);
                saveFile.saveTasks(tasks);
            } catch (InvalidCommandException e) {
                ui.printUnknownCommandMessage();
            } catch (IOException e) {
                ui.printSaveErrorMessage();
            }
        }
    }

    /**
     * Function that checks if the string is an add task command.
     * @param text String containing user input.
     * @return true if string starts with "todo", "deadline" or "event" and false otherwise.
     */
    public boolean isAddCommand(String text) {
        return text.startsWith("todo") || text.startsWith("deadline") || text.startsWith("event");
    }

    /**
     * Function that waits for user input, then executes user command.
     * "list" will list all current tasks.
     * "done x" will mark task x as done, where x is the number of the task.
     * "todo", "deadline" or "event" will attempt to add the task.
     * Any other string will print an error message.
     * @param text String containing user input.
     * @throws InvalidCommandException thrown when command is invalid.
     */
    public void getCommand(String text) throws InvalidCommandException {
        text = text.trim();
        if (text.equals("list")) {
            ui.printTasks(tasks);
        } else if (text.startsWith("done")) {
            doTask(text);
        } else if (text.startsWith("delete")) {
            deleteTask(text);
        } else if (isAddCommand(text)) {
            addTask(text);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Function that deletes specified task from the Arraylist.
     * @param text String containing user input.
     */
    public void deleteTask(String text) {
        String str = text.substring(text.indexOf("delete") + 6).trim();
        try {
            int index = Integer.parseInt(str) - 1;
            ui.printDeleteTaskMessage(tasks, index);
            tasks.remove(index);
            ui.printTaskCount(tasks.size());
        } catch (NumberFormatException e) {
            ui.printNumberFormatMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskMessage();
        }
    }

    /**
     * This function attempts to add the task specified by the user to the list and catches exceptions if the input
     * is invalid, printing error messages.
     * @param text String containing user input.
     */
    public void addTask(String text) {
        if (text.startsWith("todo")) {
            addTodo(text);
        } else if (text.startsWith("deadline")) {
            try {
                addDeadline(text);
            } catch (InvalidDeadlineException e) {
                ui.printDeadlineSyntaxMessage();
            }
        } else {
            try {
                addEvent(text);
            } catch (InvalidEventException e) {
                ui.printEventSyntaxMessage();
            }
        }
    }

    /**
     * This function adds the todo specified by the user to the list.
     * @param text String containing user input.
     */
    public void addTodo(String text) {
        String str = text.substring(text.indexOf("todo") + 4).trim();
        tasks.add(new Todo(str));
        ui.printAddTaskMessage(tasks, tasks.size() - 1);
        ui.printTaskCount(tasks.size());
    }

    /**
     * This function adds the deadline specified by the user to the list.
     * @param text String containing user input.
     * @throws InvalidDeadlineException is thrown when command syntax is not followed.
     */
    public void addDeadline(String text) throws InvalidDeadlineException  {
        if (!text.contains("/by")) {
            throw new InvalidDeadlineException();
        }
        String str = text.substring(text.indexOf("deadline") + 8, text.indexOf("/")).trim();
        String by = text.substring(text.indexOf("/by") + 3).trim();
        tasks.add(new Deadline(str, by));
        ui.printAddTaskMessage(tasks, tasks.size() - 1);
        ui.printTaskCount(tasks.size());
    }

    /**
     * This function adds the event specified by the user to the list.
     * @param text String containing user input.
     * @throws InvalidEventException is thrown when command syntax is not followed.
     */
    public void addEvent(String text) throws InvalidEventException {
        if (!text.contains("/at")) {
            throw new InvalidEventException();
        }
        String str = text.substring(text.indexOf("event") + 5, text.indexOf("/")).trim();
        String at = text.substring(text.indexOf("/at") + 3).trim();
        tasks.add(new Event(str, at));
        ui.printAddTaskMessage(tasks, tasks.size() - 1);
        ui.printTaskCount(tasks.size());
    }

    /**
     * Function that attempts to mark a task done by calling markAsDone. It prints error messages if any exceptions
     * are caught from both parseInt and markAsDone.
     * If the String given is not a number or is out of bounds, it will catch the exception and print an error message.
     * @param text String that is supposed to be the number of the task.
     */
    public void doTask(String text) {
        String str = text.substring(text.indexOf("done") + 4).trim();
        try {
            int index = Integer.parseInt(str);
            markAsDone(index - 1);
        } catch (NumberFormatException e) {
            ui.printNumberFormatMessage();
        } catch (TaskNegativeException e) {
            ui.printNegativeIndexMessage();
        } catch (TaskNotFoundException e) {
            ui.printInvalidTaskMessage();
        }
    }

    /**
     * Function throws exceptions if the index of the task is invalid and marks task as done if it is valid..
     * @param index Index of the task to be marked as done.
     * @throws TaskNegativeException If index is negative.
     * @throws TaskNotFoundException If index is of a task that has not been created yet.
     */
    public void markAsDone(int index) throws TaskNegativeException, TaskNotFoundException {
        if (index < 0) {
            throw new TaskNegativeException();
        } else if (index >= tasks.size()) {
            throw new TaskNotFoundException();
        }
        tasks.get(index).markAsDone();
        ui.printDoneTaskMessage(tasks, index);
    }
}


