import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Starting the bot
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        Task List[] = new Task[100];  //Creating a list of tasks
        int listSize = 0;

        while(true) {
            System.out.println("--------------------------------------------------------------"); //Formatting
            String line; //Input from user
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            try {
                if (line.split(" ")[0].equals("bye")) {
                    break;
                } else if (line.split(" ")[0].equals("list")) {
                    for (int i = 0; i < listSize; i++) {
                        System.out.println(i + 1 + "." + List[i]);
                    }
                } else if (line.split(" ")[0].equals("done")) {
                    //When the user keys in done
                    int taskNumber = Integer.valueOf(line.split(" ")[1]);
                    List[taskNumber - 1].setDone("X");
                } else if (line.split(" ")[0].equals("todo")) {
                    //When the user adds a todo
                    try {
                        List[listSize] = new Todo(line.split("todo ")[1]);
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    System.out.println("Got it. I've added this task:");
                    System.out.println(List[listSize]);
                    System.out.println("Now you have " + (listSize + 1) + " tasks in the list.");
                    listSize++;
                } else if (line.split(" ")[0].equals("deadline")) {
                    //When the user adds a deadline
                    List[listSize] = new Deadline(line.split("deadline ")[1], line.split("/by")[1]);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(List[listSize]);
                    System.out.println("Now you have " + (listSize + 1) + " tasks in the list.");
                    listSize++;
                } else if (line.split(" ")[0].equals("event")) {
                    //When the user adds an event
                    List[listSize] = new Event(line.split("event ")[1], line.split("/at")[1]);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(List[listSize]);
                    System.out.println("Now you have " + (listSize + 1) + " tasks in the list.");
                    listSize++;
                } else {
                    //When a user adds in a random task
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    //throw new Exception("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Exiting the bot
        System.out.println("Bye. Hope to see you again soon!");
    }
}
