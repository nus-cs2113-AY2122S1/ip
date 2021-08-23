package ip.src.main.java;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String line = null;
        Task[] tasks = {};
        int i=0;

        while (!Objects.equals(line, "bye")) {
            line = in.nextLine();
            if (Objects.equals(line, "list")) {
                System.out.println("Here are the tasks in your list:");
                Task.printList(i,tasks);
            }
            else if (Objects.equals(line, "done 2")) {
                System.out.println("Nice! I've marked this task as done: ");
                Task.printDoneList(i,tasks);
            }
            else {
                tasks = Task.addTask(i,tasks,line);
                i++;
                if (!Objects.equals(line, "bye")) System.out.println("added: "+ line);
            }

        }

        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
