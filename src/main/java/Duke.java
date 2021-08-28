import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        String phrase = sc.nextLine();

        while (!phrase.equals("bye")) {
            if (phrase.equals("list")) {
                Timetable.getTasks();
                phrase = sc.nextLine();
            } else if (phrase.contains("done")) {
                phrase = phrase.replace("done ", "");
                int taskNumber = Integer.parseInt(phrase);
                Timetable.getThingsToDo().get(taskNumber - 1).finishTask();
                phrase = sc.nextLine();
            } else {
                recordTask(phrase);
                phrase = sc.nextLine();
            }
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
    static void recordTask(String phrase) {
        System.out.println("Alright. I'll put it on the list.");

        if (phrase.contains("todo")) {
            Todo todo = new Todo(phrase.replace("todo ", ""));
            Timetable.addTasks(todo);
        } else if (phrase.contains("deadline")) {
            phrase = phrase.replace("deadline ","");
            String[] whatAndWhen = phrase.split(" /by ");
            Deadline deadline = new Deadline(whatAndWhen[0], whatAndWhen[1]);
            Timetable.addTasks(deadline);
        } else if (phrase.contains("event")) {
            phrase = phrase.replace("event ","");
            String[] whatAndWhen = phrase.split(" /at ");
            Event event = new Event(whatAndWhen[0], whatAndWhen[1]);
            Timetable.addTasks(event);
        }

        if (Timetable.size() <= 1) {
            System.out.println("For now, you have " + Timetable.size() + " task on the list");
        } else {
            System.out.println("For now, you have " + Timetable.size() + " tasks on the list");
        }
    }
}
