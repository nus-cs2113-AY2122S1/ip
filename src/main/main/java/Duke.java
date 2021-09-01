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
                    System.out.println((i+1) + "." + list[i].toString());
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
                System.out.println(" " + list[taskNum-1].toString());
                System.out.println("____________________________________________________________\n");
                continue;
            }

            if (line.toLowerCase().contains("todo")) {
                String description = line.substring(5);
                Todo t = new Todo(description);
                list[listCount] = t;
                listCount++;
            } else if (line.toLowerCase().contains("deadline")) {
                int deadlineDividerPosition = line.indexOf("/by");
                String description = line.substring(9,deadlineDividerPosition);
                String by = line.substring(deadlineDividerPosition+4);
                Deadline t = new Deadline(description,by);
                list[listCount] = t;
                listCount++;
            }   else if (line.toLowerCase().contains("event")) {
                int eventDividerPosition = line.indexOf("/at");
                String description = line.substring(6,eventDividerPosition);
                String at = line.substring(eventDividerPosition+4);
                Event t = new Event(description,at);
                list[listCount] = t;
                listCount++;
            }

            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task:\n"
                    + " " + list[listCount-1].toString() + "\n"
                    + "Now you have " + listCount + " tasks in the list.\n"
                    + "____________________________________________________________\n");

        } while (!line.toLowerCase().equals("bye"));

    }
}
