import javax.sound.midi.SysexMessage;
import java.util.Scanner;

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
                    System.out.print(i +  1);
                    System.out.println(t[i]);
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
                    System.out.println(t[task_index - 1]);
                    System.out.println("____________________________________________________________");
                    userInput = in.nextLine();
                }
                else {
                    if(userInput.startsWith("todo")) {
                        t[listIndex] = new Task(userInput.replaceFirst("todo ", ""));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println("[T]" + t[listIndex]);
                        System.out.println("Now you have " + (listIndex + 1) + " tasks in the list");
                        System.out.println("____________________________________________________________");
                        listIndex++;
                        userInput = in.nextLine();
                    }
                    else if(userInput.startsWith("event")){
                        userInput = userInput.replaceFirst("event", "");
                        t[listIndex] = new Event(userInput.substring(0, userInput.indexOf("/at")), userInput.substring(userInput.indexOf("/at")));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(t[listIndex]);
                        System.out.println("Now you have " + (listIndex + 1) + " tasks in the list");
                        System.out.println("____________________________________________________________");
                        listIndex++;
                        userInput = in.nextLine();
                    }
                    else if(userInput.startsWith("deadline")){
                        userInput = userInput.replaceFirst("deadline ", "");
                        t[listIndex] = new Deadline(userInput.substring(0, userInput.indexOf("/by")), userInput.substring(userInput.indexOf("/by")));
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(t[listIndex]);
                        System.out.println("Now you have " + (listIndex + 1) + " tasks in the list");
                        System.out.println("____________________________________________________________");
                        listIndex++;
                        userInput = in.nextLine();
                    }
                }
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
