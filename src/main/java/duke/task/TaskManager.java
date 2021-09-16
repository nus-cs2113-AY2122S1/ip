package duke.task;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    static final int INDEX_OF_KEYWORD_OF_TASK = 0;
    static final String SEPARATOR = " | ";

    private ArrayList<Task> tasks = new ArrayList<>();

    public void readInLine (String[] words) {
        if (words[0].equals("T")) {
            tasks.add(new Todo(words[2]));
            if (words[1].equals("true")) {
                tasks.get(Task.getNumberOfTasks() - 1).setDone(); //else leave isDone as false
            }
        } else if (words[0].equals("D")) {
            tasks.add(new Deadline(words[2], words[3]));
            if (words[1].equals("true")) {
                tasks.get(Task.getNumberOfTasks() - 1).setDone(); //else leave isDone as false
            }
        } else { //the only type of Task left is Event
            tasks.add(new Event(words[2], words[3]));
            if (words[1].equals("true")) {
                tasks.get(Task.getNumberOfTasks() - 1).setDone(); //else leave isDone as false
            }
        }
    }

    public void updateFile() {
        try {
            writeToFile("data/duke.txt");
        } catch (FileNotFoundException e) {
            printDividerLine();
            System.out.println("\t File not found.");
            printDividerLine();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void writeToFile(String filepath) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            if (tasks.get(i) instanceof Todo) {
                fw.write("T" + SEPARATOR + tasks.get(i).getisDone() + SEPARATOR + tasks.get(i).getDescription());
            } else if (tasks.get(i) instanceof Deadline) {
                fw.write("D" + SEPARATOR + tasks.get(i).getisDone() + SEPARATOR + tasks.get(i).getDescription()
                        + SEPARATOR + ((Deadline) tasks.get(i)).getBy());
            } else { //the final Task type can only be an event
                fw.write("E" + SEPARATOR + tasks.get(i).getisDone() + SEPARATOR + tasks.get(i).getDescription()
                        + SEPARATOR + ((Event) tasks.get(i)).getAt());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public static void printDividerLine() {
        System.out.println("\t_____________________________________________________________________________");
    }

    public void printAddedTasked() {
        printDividerLine();
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t   " + tasks.get(Task.getNumberOfTasks() - 1));
        System.out.println("\t Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        printDividerLine();
    }

    public void printIncorrectInput(boolean isEmptyDescription, String keyword) {
        printDividerLine();
        if (keyword.equalsIgnoreCase("todo")) {
            System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (keyword.equalsIgnoreCase("deadline")) {
            if (isEmptyDescription) {
                System.out.println("\t ☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                System.out.println("\t ☹ OOPS!!! The description of a deadline cannot be in the wrong format.");
            }
        } else {
            if (isEmptyDescription) {
                System.out.println("\t ☹ OOPS!!! The description of an event cannot be empty.");
            } else {
                System.out.println("\t ☹ OOPS!!! The description of an event cannot be in the wrong format.");
            }
        }
        printDividerLine();
    }

    public void addTask(String line) {
        String[] keywords = line.split(" ", 2);
        boolean hasNoException = false;
        boolean isEmptyDescription = true;

        if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("todo")) {
            try {
                tasks.add(new Todo(keywords[1]));
                hasNoException = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                printIncorrectInput(false, keywords[INDEX_OF_KEYWORD_OF_TASK]);
            }
        } else if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("deadline")) {
            try {
                String[] details = keywords[1].split(" /by ");
                isEmptyDescription = false;
                tasks.add(new Deadline(details[0], details[1]));
                hasNoException = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                printIncorrectInput(isEmptyDescription, keywords[INDEX_OF_KEYWORD_OF_TASK]);
            }
        } else if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("event")) {
            try {
                String[] details = keywords[1].split(" /at ");
                isEmptyDescription = false;
                tasks.add(new Event(details[0], details[1]));
                hasNoException = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                printIncorrectInput(isEmptyDescription, keywords[INDEX_OF_KEYWORD_OF_TASK]);
            }
        }
        if (hasNoException) {
            printAddedTasked();
            updateFile();
        }
    }

    public void listTasks() {
        printDividerLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("      " + (i + 1) + "." + tasks.get(i));
        }
        printDividerLine();
    }

    public void markAsDone(String index) {
        try {
            int indexInteger = Integer.parseInt(index);
            tasks.get(indexInteger - 1).setDone();
            printDividerLine();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + tasks.get(indexInteger - 1));
            printDividerLine();
        } catch (NumberFormatException e) {
            printDividerLine();
            System.out.println("\t ☹ OOPS!!! The input after done must be a number.");
            printDividerLine();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            printDividerLine();
            System.out.println("\t ☹ OOPS!!! The task does not exist.");
            printDividerLine();
        }
        updateFile();
    }

    public void deleteTask(String index) {
        try {
            int indexInteger = Integer.parseInt(index);
            String description = tasks.get(indexInteger - 1).toString();
            tasks.remove(indexInteger - 1);
            Task.decreaseNumberOfTasks();
            printDividerLine();
            System.out.println("\t Noted. I've removed this task:");
            System.out.println("\t  " + description);
            System.out.println("\t Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
            printDividerLine();
        } catch (NumberFormatException e) {
            printDividerLine();
            System.out.println("\t ☹ OOPS!!! The input after done must be a number.");
            printDividerLine();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            printDividerLine();
            System.out.println("\t ☹ OOPS!!! The task does not exist.");
            printDividerLine();
        }
        updateFile();
    }
}
