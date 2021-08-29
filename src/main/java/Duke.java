import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "------------------------------------------------------------------------------------------\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "Hello! I'm Duke.\n" + logo + "What can i do for you?\n" + line);

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Task[] t = new Task[100];             //contains array of null objects???
        int taskCount = 0;

        while (!input.equals("bye")) {

            if (input.equals("list")) {                             //LIST DOESN'T WORK->null pointer exception->line 23
                System.out.println(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + "[" + t[i].getStatusIcon() + "] " + t[i].getDescription() + "\n");
                }
                System.out.println(line);
            } else if (input.contains("done")) {                                                       //DONE WORKS
                String taskNumber = input.substring(input.lastIndexOf(" ") + 1);
                int finalTaskNumber = Integer.parseInt(taskNumber) - 1;
                t[finalTaskNumber].markAsDone();
                System.out.println(line + "\n" + "Kudos! One less thing to stress about!\n");
                System.out.println("  " + "[X] " + t[finalTaskNumber].getDescription() + "\n" + line);
            } else {                                                                           //ADDING NEW TASK WORKS
                t[taskCount] = new Task(input);
                taskCount++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
            input = scan.nextLine();
        }
        System.out.println(line + "\n" + "Ciao! More tasks to do later!\n" + line);                     //BYE WORKS
    }
}

