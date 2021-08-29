import java.util.Scanner;

public class Duke {

    //TODO Abstract repeated functions
    //constants / enums for TaskTypes -> Todo/Deadline/Event, then case statement
    //Super for classes

    public static void printlnTab(String str) {
        System.out.println("\t" + str);
    }

    public static void printDivider() {
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void welcomeMessage() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        printDivider();
        System.out.println(logo);
        printlnTab("Hello! I'm Duke\n\tWhat can I do for you?");
        printDivider();
    }
        

    public static void main(String[] args) {
        welcomeMessage();

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine().trim();

        Task[] tasks = new Task[100];

        //index of task in tasks array
        int tasksIndex = 0;

        //check userInput with certain command keywords

        while (!userInput.equals("bye")) {
            printDivider();

            if (userInput.equals("list")) {
                printlnTab("Here are the tasks in your list:");
                
                //listing out tasks if userInput == "list"
                for (int i = 0; i < tasksIndex; i++) {
                    printlnTab(String.format("%d.%s", (i+1), tasks[i]));
                }
                printDivider();
            } else if (userInput.startsWith("done")) {
                String taskNumberStr = userInput.substring(5);
                int taskNumber = Integer.parseInt(taskNumberStr);

                //taskNumber displayed starting with 1
                //but array starts with 0
                (tasks[taskNumber - 1]).markAsDone();

                printlnTab("Nice! I've marked this task as done:");
                printlnTab(String.format("%s", tasks[taskNumber - 1]));
                printDivider();

            } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {

                //instantiate new Task, store in array
                if (userInput.startsWith("todo")) {
                    tasks[tasksIndex] = new Todo(userInput);

                } else if (userInput.startsWith("deadline")) {
                    if (userInput.contains("/b")) {
                        userInput = userInput.substring(8).stripLeading(); // strip deadline
                        String[] deadlineDetails = userInput.split("/by");

                        if (deadlineDetails.length == 2) {
                            String description = deadlineDetails[0].strip();
                            String by = deadlineDetails[1].strip();
                            tasks[tasksIndex] = new Deadline(description, by);
                        } else {
                            //error
                            userInput = in.nextLine().trim();
                            continue;
                        }
                    } else {
                        //invalid
                        userInput = in.nextLine().trim();
                        continue;
                    }    
                } else { //events
                    // tasks[tasksIndex] = new Event(userInput);
                    if (userInput.contains("/a")) {
                        userInput = userInput.substring(5).stripLeading(); // strip deadline
                        String[] eventDetails = userInput.split("/at");

                        if (eventDetails.length == 2) {
                            String description = eventDetails[0].strip();
                            String at = eventDetails[1].strip();
                            tasks[tasksIndex] = new Event(description, at);
                        } else {
                            //error
                            userInput = in.nextLine().trim();
                            continue;
                        }
                    } else {
                        //invalid
                        userInput = in.nextLine().trim();
                        continue;
                    }    
                }

                printlnTab("Got it. I've added this task:");
                printlnTab(" " +  tasks[tasksIndex]);
                printlnTab(String.format("Now you have %d tasks", tasksIndex + 1)); // need case for odd

                printDivider();
                tasksIndex++;
            } 
            
            //catch all for invalid inputs
            userInput = in.nextLine().trim();
            continue;

        }
        printlnTab("Bye. Hope to see you again soon!");
        printDivider();
    }

}
