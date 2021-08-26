import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hi! I'm Herrick, your task manager." + "\n"
                + "What would you like to add to your timetable?";

        String farewell = "Bye. Hope you will complete everything for today!";

        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);

        String phrase = sc.nextLine();

        Tasks list = new Tasks();

        while (!phrase.equals("bye")) {
            if (phrase.equals("list")) {
                list.getTasks();
                phrase = sc.nextLine();
                continue;
            }
            list.addTasks(phrase);
            phrase = sc.nextLine();
        }

        System.out.println(farewell);
    }
}
