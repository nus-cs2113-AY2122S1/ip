import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Duke {

    public static void main(String[] args) throws DukeException{
        //Starting the bot
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        //Task List[] = new Task[100]; //Creating a list of tasks
        ArrayList<Task> List = new ArrayList<Task>();
        int listSize = 0;

        while(true) {
            System.out.println("--------------------------------------------------------------"); //Formatting
            String line; //Input from user
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            if(line.split(" ")[0].equals("bye")) {
                break;
            } else if(line.split(" ")[0].equals("list")){
                for (int i = 0; i < listSize; i++) {
                    System.out.println(i+1 + "." + List.get(i));
                }
            } else if(line.split(" ")[0].equals("done")) {
                //When the user keys in done
                int taskNumber = Integer.valueOf(line.split(" ")[1]);

                List.get(taskNumber-1).setDone("X");
            } else if(line.split(" ")[0].equals("todo")) {
                //When the user adds a todo
                try {
                    List.add(new Todo(line.split("todo ")[1]));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(List.get(listSize));
                    System.out.println("Now you have " + (listSize + 1) + " tasks in the list.");
                    listSize++;
                } catch (Exception ex){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            } else if(line.split(" ")[0].equals("deadline")) {
                //When the user adds a deadline
                List.add(new Deadline(line.split("deadline ")[1], line.split("/by")[1]));

                System.out.println("Got it. I've added this task:");
                System.out.println(List.get(listSize));
                System.out.println("Now you have " + (listSize + 1)  + " tasks in the list.");
                listSize++;
            } else if(line.split(" ")[0].equals("event")) {
                //When the user adds an event
                List.add(new Event(line.split("event ")[1], line.split("/at")[1]));

                System.out.println("Got it. I've added this task:");

                System.out.println(List.get(listSize));

                System.out.println("Now you have " + (listSize + 1)  + " tasks in the list.");
                listSize++;
            } else if(line.split(" ")[0].equals("delete")){


                System.out.println("Got it. I've removed this task:");
                System.out.println(List.get(Integer.valueOf(line.split(" ")[1])-1));
                System.out.println("Now you have " + (listSize - 1)  + " tasks in the list.");
                List.remove(Integer.valueOf(line.split(" ")[1])-1);
                listSize--;
            }
            else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        //Exiting the bot
        System.out.println("Bye. Hope to see you again soon!");
    }


}
