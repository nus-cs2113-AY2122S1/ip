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

        // Level-3
        String line;
        Scanner in = new Scanner(System.in);
        System.out.print("Type something: ");
        line = in.nextLine();

        // List of variables
        String[] task_list = new String[100];
        Task[] schedule = new Task[100];
        int total_tasks = 0;

        while (true)
        {
            // Exit

            if (line.equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // List down
            if (line.equals("list"))
            {
                for (int i = 0; i < total_tasks; i += 1)
                {
                    System.out.print((i+1) + "." + "[" + schedule[i].getStatusIcon() + "] ");
                    System.out.println(schedule[i].description);
                }
            }
            
            //Done
            else if ((line.length() > 4) && line.substring(0, 4).equals("done"))
            {
                int number = Character.getNumericValue(line.charAt(5));
                schedule[number - 1].markAsDone();
            }

            // Check for duplicates and add accordingly
            else if (diff_string_check(task_list, line))
            {
                Task t = new Task(line);
                schedule[total_tasks] = t;
                task_list[total_tasks] = line;
                total_tasks += 1;
            }

            line = in.nextLine();
        }

    }
}
