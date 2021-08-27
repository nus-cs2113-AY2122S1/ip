import java.util.Scanner;
import java.util.Arrays;
public class Duke {


    public static void main(String[] args) {

        String userInput;
        Scanner in = new Scanner(System.in);
        Task[] t = new Task[100];
        int listIndex = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        userInput = in.nextLine();

        while (!(userInput.contentEquals("bye"))) {
            if(userInput.contentEquals("list")){
                System.out.println("____________________________________________________________");
                System.out.println("List so far: ");
                for(int i = 0; i < listIndex; i++) {
                    System.out.println( (i+1) + "[" + t[i].getStatus() + "]" + t[i].name);
                }
                System.out.println("____________________________________________________________");
                userInput = in.nextLine();
            }
            else {
                if(userInput.contains("done")){
                    int task_index = Integer.parseInt(String.valueOf(userInput.charAt(userInput.length() - 1)));
                    t[task_index - 1].taskDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("List so far: ");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + t[task_index - 1].getStatus() + "] " + t[task_index - 1].name);
                    System.out.println("____________________________________________________________");
                    userInput = in.nextLine();
                }
                else {
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + userInput);
                    System.out.println("____________________________________________________________");
                    t[listIndex] = new Task(userInput);
                    listIndex++;
                    userInput = in.nextLine();
                }
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
