import java.io.File;
import java.io.FileWriter;
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

public class Shika {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public TextUi ui;

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
        File f = new File("data/ShikaTasks.txt");
        ui.printLogo();
        ui.printWelcomeMessage(setupSave(f));
        loadTasks(f);
    }

    /**
     * Function that loads tasks from the given filepath.
     * @param f path to file.
     * @throws FileNotFoundException
     */
    private static void loadTasks(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            loadTask(s.nextLine());
        }
    }

    /**
     * This function attempts to create the save file at the given path if it does not already exist.
     * @param f File to create.
     * @return true if file is created, false otherwise
     */
    private boolean setupSave(File f) {
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
            }
            if (f.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            ui.printFileErrorMessage();
        } catch (SecurityException e) {
            ui.printSecurityErrorMessage();
        }
        return false;
    }

    /**
     * This function loads tasks by parsing the String inputted, then calling addSavedTask to add it to
     * taskList.
     * @param s String to be parsed.
     */
    public static void loadTask(String s) {
        int firstDiv = s.indexOf("|") + 1;
        int secondDiv = s.indexOf("|", firstDiv) + 1;
        int thirdDiv = s.indexOf("|", secondDiv) + 1;
        char type = s.charAt(0);
        String atBy = s.substring(firstDiv, secondDiv - 1).trim();
        String name = s.substring(secondDiv, thirdDiv - 1).trim();
        String done = s.substring(thirdDiv).trim();
        addSavedTask(type, atBy, name, done);
    }

    /**
     * This function directly adds a task to tasks without printing any messages. Used for loading saved tasks.
     * @param type type of task.
     * @param atBy at/by of task, if applicable.
     * @param name name of task.
     * @param done if the task is done or not.
     */
    private static void addSavedTask(char type, String atBy, String name, String done) {
        switch(type) {
        case 'T':
            tasks.add(new Todo(name));
            break;
        case 'D':
            tasks.add(new Deadline(name, atBy));
            break;
        case 'E':
            tasks.add(new Event(name, atBy));
            break;
        default:
            return;
        }
        if (done.equals("true")) {
            tasks.get(Task.count).markAsDone();
        }
        Task.count += 1;
    }

    /**
     * This function saves tasks to data/ShikaTasks.txt. It rewrites the txt from scratch.
     * @throws IOException when the saving operation is interrupted.
     */
    public void saveTasks() throws IOException {
        FileWriter fw = new FileWriter("data/ShikaTasks.txt");
        fw.close();
        for (int i = 0; i < Task.count; i++) {
            try {
                tasks.get(i).saveTask();
            } catch (IOException e) {
                ui.printSaveErrorMessage();
            }
        }
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
                saveTasks();
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
            ui.printTasks(tasks, Task.count);
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
            Task.count -= 1;
            ui.printTaskCount(Task.count);
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
        Task.count += 1;
        ui.printAddTaskMessage(tasks, Task.count - 1);
        ui.printTaskCount(Task.count);
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
        Task.count += 1;
        ui.printAddTaskMessage(tasks, Task.count - 1);
        ui.printTaskCount(Task.count);
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
        Task.count += 1;
        ui.printAddTaskMessage(tasks, Task.count - 1);
        ui.printTaskCount(Task.count);
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
        } else if (index >= Task.count) {
            throw new TaskNotFoundException();
        }
        tasks.get(index).markAsDone();
        ui.printDoneTaskMessage(tasks, index);
    }
}


