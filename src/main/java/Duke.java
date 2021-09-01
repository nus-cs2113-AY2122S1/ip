
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Welcome Message
        printWelcomeMessage();

        // Active Chat
        activeChat();

        // Goodbye Message
        printGoodbyeMessage();
    }

    private static void activeChat() {
        boolean isBye = false;
        String input;
        Scanner in = new Scanner(System.in);
        int counter = 0;
        Task[] list = new Task[100];

        while(!isBye){
            //store input
            input = in.nextLine();
            //check if exiting while loop is required
            if (input.equals("bye")){
                isBye = true;
                continue;
            }
            //check if list
            if (input.equals("list")) {
                //prints the list
                System.out.println("--------------------");
                if (list[0] != null){
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < counter; i += 1){
                        printTask(list[i], i);
                    }
                } else {
                    //when list is empty and input contains 'list'
                    System.out.println("List is empty. Time to get productive!");
                }
                System.out.println("--------------------");
                continue;
            }
            //check if done
            if (input.contains("done") && list[0] != null ) {
                //when input contains done
                System.out.println("--------------------");
                int donePos = input.indexOf("done"); //finds pos of 'done'
                //when input contains done but no number
                if(input.length() < donePos + 5){
                    System.out.println("Please specify which task is done.");
                    continue;
                }
                //when input contains done and specified number
                String itemNumDone = input.substring(donePos + 5, donePos + 6);
                int itemNum = Integer.parseInt(itemNumDone);
                list[itemNum - 1].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println( itemNum + ".[" + list[itemNum - 1].getStatusIcon() + "] " + list[itemNum - 1].getDescription() );
                System.out.println("--------------------");
                continue;
            } else if (input.contains("done") && list[0] == null) {
                //when list is empty and input contains 'done'
                System.out.println("--------------------");
                System.out.println("Unable to tick off list.");
                System.out.println("List is empty. Time to get productive!");
                System.out.println("--------------------");
                continue;
            }
            //check for type of input
            if (input.contains("todo")) {
                String description = input.substring(5);
                ToDo newTask = new ToDo(description);
                list[counter] = newTask;
                counter += 1;
                printAddedTaskMessage(newTask, counter);
                continue;
            } else if (input.contains("deadline")) {
                int donePos = input.indexOf("/"); //finds pos of '/'
                String description = input.substring(9,donePos);
                String date = input.substring(donePos + 4);
                Deadline newTask = new Deadline(description,date);
                list[counter] = newTask;
                counter += 1;
                printAddedTaskMessage(newTask, counter);
                continue;
            } else if (input.contains("event")) {
                int donePos = input.indexOf("/"); //finds pos of '/'
                String description = input.substring(6,donePos);
                String date = input.substring(donePos + 4);
                Event newTask = new Event(description,date);
                list[counter] = newTask;
                counter += 1;
                printAddedTaskMessage(newTask, counter);
                continue;
            } else {
                System.out.println("Please specify tasks: todo, deadline or event");
                System.out.println("Example - type in the following: todo read book");
                continue;
            }
        }
    }

    private static void printAddedTaskMessage(Task task, int i) {
        System.out.println("--------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + task.getLetter() + "] "
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getDate() );
        System.out.println("Now you have " + i + " tasks in the list.");
        System.out.println("--------------------");
    }

    private static void printTask(Task task, int i) {
        System.out.println(i + 1
                + ".[" + task.getLetter() + "] "
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription() );
    }

    private static void printGoodbyeMessage() {
        System.out.println("--------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("");
        System.out.println("--------------------");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("--------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");
        System.out.println("--------------------");
    }
}
