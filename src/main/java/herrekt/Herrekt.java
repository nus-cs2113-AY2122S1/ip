package herrekt;

import herrekt.exceptions.InvalidInputException;
import herrekt.exceptions.NoTaskException;
import herrekt.taskmanager.Deadline;
import herrekt.taskmanager.Event;
import herrekt.taskmanager.Timetable;
import herrekt.taskmanager.Todo;

import java.util.Scanner;

public class Herrekt {
    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        String phrase = sc.nextLine();

        while (!phrase.equals("bye")) {
            if (phrase.equals("list")) {
                Timetable.getTasks();
            } else if (phrase.contains("done")) {
                int taskNumber = parseDoneInputToInt(phrase);
                Timetable.updateTasks(taskNumber);
            } else {
                try {
                    isInputValid(phrase);
                    recordTask(phrase);
                } catch (InvalidInputException e1) {
                    System.out.println("ERROR! Please follow format in README.md" + "\n"
                            + "Your input: " + phrase);
                } catch (NoTaskException e2) {
                    System.out.println("ERROR! What is the task?" + "\n"
                            + "Your input: " + phrase);
                }
            }
            phrase = sc.nextLine();
        }

        printFarewellMessage();
    }

    static void printWelcomeMessage() {
        String greeting = "Hi! I'm Herrick, your task manager." + "\n"
                + "What would you like to add to your timetable?";
        System.out.println(greeting);
    }

    static void printFarewellMessage() {
        System.out.println("Bye. Hope you will complete everything for today!");
    }

    static void printNumberOfTasks() {
        if (Timetable.getSize() <= 1) {
            System.out.println("For now, you have "
                    + Timetable.getSize()
                    + " task on the list");
        } else {
            System.out.println("For now, you have "
                    + Timetable.getSize()
                    + " tasks on the list");
        }
    }

    static int parseDoneInputToInt(String phrase) {
        phrase = phrase.replace("done ", "");
        return Integer.parseInt(phrase);
    }

    static Timetable parsePhraseToTask(String phrase) {
        Timetable task = null;
        if (phrase.contains("todo")) {
            task = new Todo(phrase.replace("todo ", ""));
        } else if (phrase.contains("deadline")) {
            phrase = phrase.replace("deadline ","");
            String[] taskAndTime = phrase.split(" /by ");
            task = new Deadline(taskAndTime[0], taskAndTime[1]);
        } else if (phrase.contains("event")) {
            phrase = phrase.replace("event ","");
            String[] taskAndTime = phrase.split(" /at ");
            task = new Event(taskAndTime[0], taskAndTime[1]);
        }
        return task;
    }

    static void recordTask(String phrase) {
        try {
            Timetable task = parsePhraseToTask(phrase);
            Timetable.addTask(task);
            printNumberOfTasks();
        } catch (ArrayIndexOutOfBoundsException e3) {
            System.out.println("ERROR! I tried to "
                    + "Please separate deadline/event and time with '/by' and '/at' respectively."
                    + "\n" + "Your input: " + phrase);
        }
    }
    static void isInputValid(String phrase) throws InvalidInputException, NoTaskException {
        if (!(phrase.contains("todo")
                || phrase.contains("deadline")
                || phrase.contains("event"))
            || !(phrase.split(" ")[0].equals("todo")
                || phrase.split(" ")[0].equals("deadline")
                || phrase.split(" ")[0].equals("event"))) {
            throw new InvalidInputException("ERROR! Please follow format in README.md" + "\n"
                    + "Your input: " + phrase);
        } else if (phrase.split(" ").length == 1) {
            throw new NoTaskException("ERROR! What is the task?" + "\n"
                    + "Your input: " + phrase);
        }
    }
}
