package duke;

import duke.task.*;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static final ArrayList<Task> listInput = new ArrayList<>();
    public static final String BREAKER = "......................................................................";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + BREAKER + "\n" + "Hi! I'm Duke.\n" + "How can I make your life easier?\n" + BREAKER);
        int taskNumber = 0;
        taskNumber = getFromFile(listInput, taskNumber);
        Scanner input = new Scanner(System.in);
        String lineInput = "";
        while (!lineInput.equals("bye")) {
            lineInput = input.nextLine();
            String[] arrayInput = lineInput.split(" ");
            String commandInput = arrayInput[0];
            System.out.println(BREAKER);
            if (commandInput.equals(("bye"))) {
                break;
            }
            try {
                switch (commandInput) {
                case "list":
                    try {
                        showTask(listInput, taskNumber);
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("OOPS!!! The index of the task that you entered does not exist:(\n");
                    }
                    break;
                case "done":
                    try {
                        doneTask(listInput, arrayInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("OOPS!!! The index of the task that you entered does not exist:(\n");
                    }
                    break;
                case "deadline":
                case "event":
                case "todo":
                    try {
                        recordTask(listInput, lineInput, taskNumber, commandInput);
                        taskNumber++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The name of the task that you entered is invalid:(\n");
                    }
                    break;
                case "delete":
                    try {
                        deleteTask(listInput, arrayInput, taskNumber);
                        taskNumber--;
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The index of the task that you entered does not exist:(\n");
                    }
                    break;
                default:
                    throw new DukeException("OOPS!!! Sorry, but I do not understand:(\n");
                }
                saveToFile(listInput, taskNumber);
            } catch (IOException | DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(BREAKER);
        }

        System.out.println("Byebye! Have a wonderful day!\n" + BREAKER);
    }

    private static void showTask(ArrayList listInput, int taskNumber) {
        if (taskNumber == 0) {
            System.out.println("The list is empty! Add something to it:)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskNumber; i++) {
                System.out.println((i + 1) + "." + listInput.get(i).toString());
            }
        }
    }

    private static void doneTask(ArrayList listInput, String arrayInput) {
        int inputIndex = Integer.parseInt(arrayInput) - 1;
        ((Task) listInput.get(inputIndex)).markAsDone();
        System.out.println("Wonderful! This task is now marked as done:\n" + listInput.get(inputIndex).toString());
    }

    private static void deleteTask(ArrayList listInput, String[] arrayInput, int taskNumber) {
        int inputIndex = Integer.parseInt(arrayInput[1]) - 1;
        System.out.println("Alright, the following task has been removed\n" + listInput.get(inputIndex).toString() + "\n" + "Now you have " + (taskNumber - 1) + " tasks left");
        listInput.remove(inputIndex);
    }

    private static void recordTask(ArrayList listInput, String lineInput, int taskNumber, String commandInput) throws DukeException {
        switch (commandInput) {
        case "event":
            recordEvent(listInput, lineInput, taskNumber);
            break;
        case "deadline":
            recordDeadline(listInput, lineInput, taskNumber);
            break;
        case "todo":
            recordToDo(listInput, lineInput, taskNumber);
            break;
        }
        System.out.println("Got it. I've added this task:\n" + listInput.get(taskNumber).toString() + "\n" + "Now you have " + (taskNumber + 1) + " tasks in your list");
    }

    private static void recordDeadline(ArrayList listInput, String lineInput, int taskNumber) throws DukeException {
        int breakIndex = lineInput.indexOf("/");
        if (lineInput.length() < 12) {
            throw new DukeException("The description of the deadline is too short! Please enter again.\n");
        }
        String deadlineName = lineInput.substring(9, breakIndex);
        String deadlineDate = lineInput.substring(breakIndex + 3);
        listInput.add(taskNumber, new Deadline(deadlineName, deadlineDate));
    }

    private static void recordEvent(ArrayList listInput, String lineInput, int taskNumber) throws DukeException {
        int breakIndex = lineInput.indexOf("/");
        if (lineInput.length() < 9) {
            throw new DukeException("The description of the event is too short! Please enter again.\n");
        }
        String eventName = lineInput.substring(6, breakIndex);
        String eventDate = lineInput.substring(breakIndex + 3);
        listInput.add(taskNumber, new Event(eventName, eventDate));
    }

    private static void recordToDo(ArrayList listInput, String lineInput, int taskNumber) {
        String todoName = lineInput.substring(5);
        listInput.add(taskNumber, new ToDo(todoName));
    }

    public static void saveToFile(ArrayList listInput, int numTask) throws IOException {
        FileWriter file = new FileWriter("src/main/java/duke/data/task.txt");
        for (int i = 0; i < numTask; i++) {
            String saveInput = ((Task) listInput.get(i)).getType() + " | " + getNumberStatus(listInput, i) + " | " + ((Task) listInput.get(i)).getName() + "\n";
            file.write(saveInput);
        }
        file.close();
    }

    private static String getNumberStatus(ArrayList listInput, int taskNumber) {
        if (((Task) listInput.get(taskNumber)).getStatus().equals("[X]")) {
            return "1";
        } else {
            return "0";
        }
    }

    private static int getFromFile(ArrayList listInput, int taskNumber) {
        try {
            File file = new File("src/main/java/duke/data/task.txt");
            Scanner updateInput = new Scanner(file);
            while (updateInput.hasNext()) {
                String[] lineInput = updateInput.nextLine().split("\\|");
                String taskType = lineInput[0];
                switch (taskType) {
                case "D ":
                    recordDeadline(listInput, "deadline" + lineInput[2], taskNumber);
                    break;
                case "E ":
                    recordEvent(listInput, "event" + lineInput[2], taskNumber);
                    break;
                case "T ":
                    recordToDo(listInput, "todo" + lineInput[2], taskNumber);
                    break;
                }
                if (lineInput[1].equals("1")) {
                    ((Task) listInput.get(taskNumber)).markAsDone();
                }
                taskNumber++;
            }
        } catch (IOException e) {
            System.out.println("There is no saved file:( but I will create a new one for you:)\n" + BREAKER);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return taskNumber;
    }
}