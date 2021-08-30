import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static boolean diff_string_check(String[] arr, String toCheckValue)
    {
        boolean test = true;

        for (int i = 0; i < arr.length; i++)
        {
            if (toCheckValue.equals(arr[i]))
            {
                test = false;
                break;
            }
            else if (arr[i] == null)
            {
                break;
            }
        }

        return test;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Level-2
        String line;
        Scanner in = new Scanner(System.in);
        System.out.print("Type something: ");
        line = in.nextLine();

        String[] task_list = new String[100];

        int total_tasks = 0;
        while (true)
        {
            if (line.equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (line.equals("list"))
            {
                for (int i = 0; i < total_tasks; i += 1)
                {
                    System.out.println((i+1) + ". " + task_list[i]);
                }
                line = in.nextLine();
            }
            else if (diff_string_check(task_list, line))
            {
                System.out.println("added: " + line);
                task_list[total_tasks] = line;
                total_tasks += 1;
                line = in.nextLine();
            }
            else
            {
                line = in.nextLine();
            }
        }

    }
}
