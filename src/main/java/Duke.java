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
        System.out.println("Bye. Hope you will complete everything for today!");
    }

    static void printPutTaskOnList() {
        System.out.println("Alright. I'll put it on the list.");
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
        Timetable task;

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
        } else {
            task = new Task("Invalid Task");
        }
        return task;
    }

    static void recordTask(String phrase) {
        printPutTaskOnList();

        Timetable task = parsePhraseToTask(phrase);
        Timetable.addTask(task);

        printNumberOfTasks();
    }
}
