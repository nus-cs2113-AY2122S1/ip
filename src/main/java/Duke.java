import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Implementing Increment Level-0: Initial skeleton of Duke
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        Task List[] = new Task[100];
        int listSize = 0;
        while(true){

            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            if(line.split(" ")[0].equals("bye")){
                break;
            }else if(line.split(" ")[0].equals("list")){
                for (int i = 0; i < listSize; i++){
                    System.out.println(i+1 + "." + List[i]);
                }
            }else if(line.split(" ")[0].equals("done")){
                int taskNumber = Integer.valueOf(line.split(" ")[1]);
                List[taskNumber-1].setDone("X");
            }else if(line.split(" ")[0].equals("todo")){
                List[listSize] = new Todo(line.split("todo")[1]);

                System.out.println("Got it. I've added this task:");
                System.out.println(List[listSize]);
                System.out.println("Now you have " + (listSize + 1)  + " tasks in the list.");
                listSize++;
            }else if(line.split(" ")[0].equals("deadline")){
                List[listSize] = new Deadline(line.split("deadline")[1], line.split("/by")[1]);

                System.out.println("Got it. I've added this task:");
                System.out.println(List[listSize]);
                System.out.println("Now you have " + (listSize + 1)  + " tasks in the list.");
                listSize++;
            }else if(line.split(" ")[0].equals("event")){
                List[listSize] = new Event(line.split("event")[1], line.split("/at")[1]);

                System.out.println("Got it. I've added this task:");
                System.out.println(List[listSize]);
                System.out.println("Now you have " + (listSize + 1)  + " tasks in the list.");
                listSize++;
            }
            else{
                List[listSize] = new Task(line);
                System.out.println("added: " + line);
                listSize++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
