import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "_________________________________________________\n";

        System.out.println(logo + "Hello! I'm Duke\n" + "Whatchu want\n" + logo);

        Scanner in = new Scanner(System.in);

        String[] List = new String[100];
        Task[] Task = new Task[100];
        String word;
        int Count = 0;

        do {
            word = in.nextLine();
            String[] words = word.split(" ");

            if (isDone(words[0])) {
                int i = Integer.parseInt(words[1]) -1;
                Task[i].markAsDone();
                System.out.println(logo + "Nice! I've marked this task as done:\n" + "[" + Task[i].getStatusIcon() + "] " + List[i] + "\n" + logo);
                //System.out.println(Arrays.toString(words) + Task[number].getStatusIcon());
            }

            else if (!isBye(word) && !isList(word)) {
                List[Count] = word;
                Task[Count] = new Task(word);
                System.out.println(logo + "added: " + word + "\n" + logo);

                Count++;
            }

            else if (isList(word)) {
                System.out.println(logo + "Here are the tasks in your list:");
                for (int i = 0; i < Count; i++) {
                    System.out.println((i + 1) + ". [" + Task[i].getStatusIcon() + "] " + List[i]);
                }
                System.out.print(logo);
            }


        } while (!isBye(word));

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


}
