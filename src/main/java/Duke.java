import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "_________________________________________________\n";

        System.out.println(logo + "Hello! I'm Duke\n" + "Whatchu want\n" + logo);

        Scanner in = new Scanner(System.in);

        Task[] Task = new Task[100];
        String word;
        String date = "";
        int Count = 0;

        do {
            word = in.nextLine();
            String[] words = word.split(" "); // stores string of multiple words in a string Array

            if (isDone(words[0])) { //complete Tasks
                int i = Integer.parseInt(words[1]) - 1; // gets Task number
                Task[i].setDone();
                System.out.println(logo + "Nice! I've marked this task as done:\n" + Task[i] + "\n" + logo);
            }

            else if (!isBye(word) && !isList(word)) { //Task words
                if (isTodo(words[0])) { //Todo task
                    Task[Count] = new Todo(word);
                    Count++;
                }
                else if (isDeadline(words[0])) { //Deadline task
                    date = word.substring(word.lastIndexOf("/") + 1);
                    Task[Count] = new Deadline(word, date);
                    Count++;
                }

                else if (isEvent(words[0])) { //Event task
                    date = word.substring(word.lastIndexOf("/") + 1);
                    Task[Count] = new Event(word, date);
                    Count++;
                }

                System.out.println(logo + "Added:\n" + Task[Count-1] + "\n" + "Now you have " + Count  + " tasks in the list.\n" + logo);
            }

            else if (isList(word)) { //List tasks
                System.out.println(logo + "Here are the tasks in your list:");
                for (int i = 0; i < Count; i++) {
                    System.out.print(i + 1 + ". ");
                    System.out.println(Task[i]);
                }
                System.out.print(logo);
            }


        } while (!isBye(word)); //Exit

        System.out.println(logo + "Bye. Hope to see you again soon!\n" + logo);

    }

    public static boolean isBye(String word) {
        return word.equals("Bye") || word.equals("bye");
    }

    public static boolean isList(String word) {
        return word.equals("List") || word.equals("list");
    }

    public static boolean isDone(String word) {
        return word.equals("Done") || word.equals("done");
    }

    public static boolean isTodo(String word) {
        return word.equals("Todo") || word.equals("todo");
    }

    public static boolean isDeadline(String word) {
        return word.equals("Deadline") || word.equals("deadline");
    }

    public static boolean isEvent(String word) {
        return word.equals("Event") || word.equals("event");
    }
}
