package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);

        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        Scanner in = new Scanner(System.in);
        String line;
        //create an array of Tasks
        Task[] list = new Task[100];
        //keep position count for array
        int listCount = 0;

        do {
            line = in.nextLine();
            if (line.toLowerCase().equals("bye")){
                //end the loop if user says bye
                System.out.println(exit);
                break;
            } else if (line.toLowerCase().equals("list")){
                //display list if user says list
                System.out.println("____________________________________________________________\n"
                        + "Here are the tasks in your list:");
                for(int i = 0; i < listCount; i++){
                    System.out.println((i+1) + ".[" + list[i].getStatusIcon() + "] " + list[i].description);
                }
                System.out.println("____________________________________________________________\n");
                continue;
            } else if (line.toLowerCase().contains("done")){
                //mark task as done if user says done
                //find divider pos between done and int
                int dividerPosition = line.indexOf(" ");
                //convert to int from str
                int taskNum = Integer.parseInt(line.substring(dividerPosition+1));
                //mark the task as done
                list[taskNum-1].markAsDone();
                System.out.println("____________________________________________________________\n"
                        + "Nice! I've marked this task as done:");
                System.out.println(" [" + list[taskNum-1].getStatusIcon() + "] " + list[taskNum-1].description);
                System.out.println("____________________________________________________________\n");
                continue;
            }
            System.out.println("____________________________________________________________\n"
                    + "added: " + line + "\n"
                    + "____________________________________________________________\n");
            Task t = new Task(line);
            list[listCount] = t;
            listCount++;
        } while (!line.toLowerCase().equals("bye"));

    }
}
