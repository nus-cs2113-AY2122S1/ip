import java.util.Scanner;

public class Duke {
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
                recordTask(phrase);
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
        String farewell = "Bye. Hope you will complete everything for today!";
        System.out.println(farewell);
    }

    static void printPutTaskOnList() {
        String toPrint = "Alright. I'll put it on the list.";
        System.out.println(toPrint);
    }

    static void printNumberOfTasks() {
        if (Timetable.size() <= 1) {
            System.out.println("For now, you have " + Timetable.size() + " task on the list");
        } else {
            System.out.println("For now, you have " + Timetable.size() + " tasks on the list");
        }
    }

    static int parseDoneInputToInt(String phrase) {
        phrase = phrase.replace("done ", "");
        return Integer.parseInt(phrase);
    }

    static Timetable parsePhraseToTask(String phrase) {
        Timetable task;

        if (phrase.contains("todo")) {
            task = new Todo(phrase.replace("todo ", ""));
        } else if (phrase.contains("deadline")) {
            phrase = phrase.replace("deadline ","");
            String[] whatAndWhen = phrase.split(" /by ");
            task = new Deadline(whatAndWhen[0], whatAndWhen[1]);
        } else if (phrase.contains("event")) {
            phrase = phrase.replace("event ","");
            String[] whatAndWhen = phrase.split(" /at ");
            task = new Event(whatAndWhen[0], whatAndWhen[1]);
        } else {
            task = new Task("Invalid Task");
        }
        return task;
    }

    static void recordTask(String phrase) {
        printPutTaskOnList();

        Timetable task = parsePhraseToTask(phrase);
        Timetable.addTasks(task);

        printNumberOfTasks();
    }
}
