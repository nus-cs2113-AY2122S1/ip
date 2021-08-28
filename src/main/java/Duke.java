package ip.src.main.java;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static String[] letter = new String[100];
    private static int i = 0;

    public static void addTask(Task t, int type){
        tasks[i] = t;
        i++;
        if (type == 1) letter[i] = "D";
        else if (type == 2) letter[i] = "E";
        else if (type == 3) letter[i] = "T";
    }

    public static void printTask(Task[] args){
        for (int j=0; j<i; j++) System.out.println(j+1 + ". "
                + "[" + letter[j+1] + "] "
                + "[" + args[j].getStatusIcon() + "] " +
                args[j].description());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String line = null;
        String[] newline;

        while (!Objects.equals(line, "bye")) {
            line = in.nextLine();
            newline = line.split("/");
            String[] arr = line.split(" ", 2);
            if (Objects.equals(line, "list")) {
                System.out.println("Here are the tasks in your list:");
                printTask(tasks);
            }
            else if (Objects.equals(arr[0], "done")) {
                System.out.println("Nice! I've marked Task " + arr[1] + " as done!");
                Task.markDone(Integer.parseInt(arr[1]),tasks);
            }
            else if (Objects.equals(arr[0], "d")) {
                System.out.println("Gotcha! I've added this deadline");
                Deadline t = new Deadline(newline[0], newline[1]);
                addTask(t,1);
            }
            else if (Objects.equals(arr[0], "e")) {
                System.out.println("Nice! I've added this event");
                Event t = new Event(newline[0], newline[1]);
                addTask(t,2);
            }
            else if (Objects.equals(arr[0], "t")){
                System.out.println("Gotcha! I've added this todo");
                Task t = new Task(line);
                addTask(t,3);
                if (!Objects.equals(line, "bye")) System.out.println("added: "+ line);
            }
            else {
                System.out.println("Sorry please rephrase");
            }
        }

        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
