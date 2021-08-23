package ip.src.main.java;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static String[] addLine(String[] arr, String line)
    {
        int i;
        String[] newarr = new String[arr.length + 1];

        for (i = 0; i < arr.length; i++) newarr[i] = arr[i];

        newarr[i] = line;

        return newarr;


    }

    public static void printList(String[] arr)
    {
        for (int i = 0; i < arr.length; i++) System.out.println(i+1 + ". " + arr[i]);
    }


    public static void main(String[] args) {
        int i=0;
        String[] arr = {};

        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String line = null;

        while (!Objects.equals(line, "bye")) {
            line = in.nextLine();
            if (Objects.equals(line, "list")) printList(arr);
            else {
                arr = addLine(arr, line);
                if (!Objects.equals(line, "bye")) System.out.println("added: "+ line);
            }

        }

        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
