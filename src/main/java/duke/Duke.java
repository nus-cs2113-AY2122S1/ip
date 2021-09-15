package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Chatbot program Duke that tracks user tasks.
 *
 * @author Leow Yuan Yang
 * @version 4.0
 * @since 2021-08-25
 */
public class Duke {
    private static final String SEPARATOR = "\t==============================================";
    private static final String logoArt = "\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@..............@@@@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@(...................@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@.....................@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@.................../@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@@.................../@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@((#@.((((((((((............@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@#((((((((((((((/(((/.........@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@%#(((((((((((((((//*(/((/,@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@#####(((((((((((/(((/(((@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@#########((((/(((((((@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@%######(####%#&@@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@(.,,*/(###((((#/##@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@(...,,*//(((((((###/#@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@* ..,,((((((((((####/#@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@((((((((((((((((####/#%@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@((((((((((((((#######@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@@@@@@%((((((((###&@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
    private static final String nameArt = "\t _______  __   __  __   __  ______    _______ \n" +
            "\t|       ||  |_|  ||  | |  ||    _ |  |       |\n" +
            "\t|  _____||       ||  | |  ||   | ||  |    ___|\n" +
            "\t| |_____ |       ||  |_|  ||   |_||_ |   |___ \n" +
            "\t|_____  ||       ||       ||    __  ||    ___|\n" +
            "\t _____| || ||_|| ||       ||   |  | ||   |    \n" +
            "\t|_______||_|   |_||_______||___|  |_||___|    ";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "data/userData.txt";
    private static final String FILE_DIRECTORY = "data";

    public static void main(String[] args) {
        boolean isDone = false;
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();
        loadData();

        do {
            // Read inputs and split them into substrings
            String[] userInputs = readUserInput(in);
            String command = userInputs[0];

            // Execute commands
            switch (command) {
            case "bye":
                isDone = true;
                saveData();
                printExitMessage();
                break;
            case "list":
                printTaskList();
                break;
            case "done":
                try {
                    executeDone(userInputs);
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "todo":
                try {
                    executeTodo(userInputs);
                } catch (EmptyDescriptionException exception) {
                    printEmptyDescriptionErrorMessage();
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "deadline":
                try {
                    executeDeadline(userInputs);
                } catch (EmptyTimeFieldException exception) {
                    printEmptyTimeFieldErrorMessage();
                } catch (EmptyDescriptionException exception) {
                    printEmptyDescriptionErrorMessage();
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "event":
                try {
                    executeEvent(userInputs);
                } catch (EmptyTimeFieldException exception) {
                    printEmptyTimeFieldErrorMessage();
                } catch (EmptyDescriptionException exception) {
                    printEmptyDescriptionErrorMessage();
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            case "delete":
                try {
                    executeDelete(userInputs);
                } catch (Exception exception) {
                    printErrorMessage();
                }
                break;
            default:
                printErrorMessage();
                break;
            }
        } while (!isDone);
    }

    private static void saveData() {
        try {
            writeToFile();
        } catch (IOException exception) {
            System.out.println("something went wrong while saving..." + exception.getMessage());
        }
    }

    private static void writeToFile() throws  IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            String taskData = "";
            if (t instanceof Todo) {
                taskData = t.getTaskIcon().concat(" | ")
                        .concat(t.getStatusIcon()).concat(" | ")
                        .concat(t.getDescription()).concat(System.lineSeparator());
            } else if (t instanceof Event || t instanceof Deadline){
                taskData = t.getTaskIcon().concat(" | ")
                        .concat(t.getStatusIcon()).concat(" | ")
                        .concat(t.getDescription()).concat(" | ")
                        .concat(t.getDue()).concat(System.lineSeparator());
            }
            fileWriter.write(taskData);
        }
        fileWriter.close();
    }

    private static void loadData() {
        try {
            File file = loadFile();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                String data = scanner.nextLine();
                String[] dataSubstrings = data.split("\\|");
                String type = dataSubstrings[0].strip();
                boolean done = dataSubstrings[1].strip().equals("X");
                String description = dataSubstrings[2].strip();
                String due;

                switch (type) {
                case "T":
                    Task todoTask = new Todo(description);
                    if (done) {
                        todoTask.setDone();
                    }
                    tasks.add(todoTask);
                    //taskList[Task.getNumOfTasks() - 1] = todoTask;
                    break;
                case "D":
                    due = dataSubstrings[3].strip();
                    Task deadlineTask = new Deadline(description, due);
                    if (done) {
                        deadlineTask.setDone();
                    }
                    //taskList[Task.getNumOfTasks() - 1] = deadlineTask;
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    due = dataSubstrings[3].strip();
                    Task eventTask = new Event(description, due);
                    if (done) {
                        eventTask.setDone();
                    }
                    //taskList[Task.getNumOfTasks() - 1] = eventTask;
                    tasks.add(eventTask);
                    break;
                }
            }
        } catch (IOException exception) {
            System.out.println("oops something went wrong..." + exception.getMessage());
        }
    }

    private static File loadFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            File dir = new File(FILE_DIRECTORY);
            dir.mkdir();
            File newFile = new File(FILE_PATH);
            newFile.createNewFile();
            return newFile;
        } else {
            return file;
        }
    }

    private static void executeEvent(String[] userInputs) throws EmptyDescriptionException, EmptyTimeFieldException {
        if (userInputs.length < 2) {
                throw new EmptyDescriptionException();
        }
        if (!userInputs[1].contains("/at")) {
            throw new EmptyTimeFieldException();
        }
        String eventDescription = userInputs[1]
                .substring(0, userInputs[1].indexOf("/"))
                .strip();
        if (eventDescription.isBlank()) {
            throw new EmptyDescriptionException();
        }
        String eventAt = userInputs[1].substring((userInputs[1]
                        .indexOf("/") + 3))
                .strip();
        Task newTask = new Event(eventDescription, eventAt);
        tasks.add(newTask);
        printAddTask(newTask);
    }

    private static void executeDeadline(String[] userInputs) throws EmptyDescriptionException, EmptyTimeFieldException {
        if (userInputs.length < 2) {
                throw new EmptyDescriptionException();
        }
        if (!userInputs[1].contains("/by")) {
            throw new EmptyTimeFieldException();
        }
        String deadlineDescription = userInputs[1]
                .substring(0, userInputs[1].indexOf("/"))
                .strip();
        if (deadlineDescription.isBlank()) {
            throw new EmptyDescriptionException();
        }
        String deadlineBy = userInputs[1]
                .substring((userInputs[1].indexOf("/") + 3))
                .strip();
        Task newTask = new Deadline(deadlineDescription, deadlineBy);
        tasks.add(newTask);
        printAddTask(newTask);
    }

    private static void executeTodo(String[] userInputs) throws EmptyDescriptionException {
        if (userInputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String todoDescription = userInputs[1].strip();
        Task newTask = new Todo(todoDescription);
        tasks.add(newTask);
        printAddTask(newTask);
    }

    private static void executeDone(String[] userInputs) {
        int itemNum = Integer.parseInt(userInputs[1].replaceAll("[^0-9]", ""));
        Task doneTask = tasks.get(itemNum - 1);
        doneTask.setDone();
        Task.decrementNumOfTasks();
        printDoneTask(doneTask, itemNum);
    }

    private static void executeDelete(String[] userInputs) {
        int itemNum = Integer.parseInt(userInputs[1].replaceAll("[^0-9]", ""));
        Task deletedTask = tasks.get(itemNum - 1);
        tasks.remove(itemNum - 1);
        printDeleteTask(deletedTask);
    }

    private static String[] readUserInput(Scanner in) {
        System.out.println("\tCall out a smurf to do a job for you!");
        String userInput = in.nextLine();
        return userInput.strip().split(" ", 2);
    }

    private static void printWelcomeMessage() {
        System.out.println(logoArt + "\n" + nameArt);
        System.out.println(SEPARATOR);
        System.out.println("\t...la la la la la la sing a happy song\n");
        System.out.println("\tWelcome to Smurf Village.");
        System.out.println("\tStart smurfing now!!");
        System.out.println(SEPARATOR);
    }

    private static void printDoneTask(Task task, int itemNum) {
        System.out.println(SEPARATOR);
        System.out.println("\tBrainy Smurf: aah another thing done");
        System.out.printf("\t%d. [%s][%s] %s\n", itemNum, task.getTaskIcon(), task.getStatusIcon(),
                task.getDescription());
        System.out.println(SEPARATOR);
    }

    private static void printDeleteTask(Task deletedTask) {
        System.out.println(SEPARATOR);
        System.out.println("\tI will get Weakling smurf to do it for you.");
        System.out.printf("\t   [%s][%s] %s\n", deletedTask.getTaskIcon(), deletedTask.getStatusIcon(),
                deletedTask.getDescription());
        System.out.println(SEPARATOR);
    }

    private static void printAddTask(Task task) {
        System.out.println(SEPARATOR);
        System.out.println("\tHandy Smurf is here to give you a hand!");
        System.out.println("\tI have added: ");
        System.out.println("\t" + (tasks.indexOf(task) + 1) + task);
        System.out.println(SEPARATOR);
    }

    private static void printTaskList() {
        System.out.println(SEPARATOR);
        System.out.println("\t\"Tracker Smurf!! I need you here!!\"");
        for (Task task : tasks) {
            System.out.println("\t" + (tasks.indexOf(task) + 1) + task);
        }
        System.out.println(SEPARATOR);
    }

    private static void printErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\toops i can't find a smurf to help you with your task.");
        System.out.println(SEPARATOR);
    }

    private static void printEmptyDescriptionErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tHey you needa gimme a description!!");
        System.out.println(SEPARATOR);
    }

    private static void printEmptyTimeFieldErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tHey you didn't provide a time :(");
        System.out.println(SEPARATOR);
    }

    private static void printExitMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\toh shucks! Gargamel is here..we gotta hide");
        System.out.println(SEPARATOR);
    }
}
