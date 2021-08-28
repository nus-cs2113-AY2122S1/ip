import java.util.Scanner;

public class Duke {

    //TODO Abstract repeated functions
    //println with tab
    //println ________
    //printing a task properly (override toString method in class)
    //constants / enums



    public static void welcomeMessage() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println(logo);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");
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
            System.out.println("\t____________________________________________________________");

            if (userInput.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                
                //listing out tasks if userInput == "list"
                for (int i = 0; i < tasksIndex; i++) {
                    System.out.println("\t" + tasks[i]);
                }
                System.out.println("\t____________________________________________________________");
            } else if (userInput.startsWith("done")) {
                String taskNumberStr = userInput.substring(5);
                int taskNumber = Integer.parseInt(taskNumberStr);

                //taskNumber displayed starting with 1
                //but array starts with 0
                (tasks[taskNumber - 1]).markAsDone();

                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  [X] " + (tasks[taskNumber - 1]).getDescription());

            } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {

                //instantiate new Task, store in array
                if (userInput.startsWith("todo")) {
                    tasks[tasksIndex] = new Todo(userInput);

                } else if (userInput.startsWith("deadline")) {
                    if (userInput.contains("/b")) {
                        userInput = userInput.substring(8).stripLeading(); // strip deadline
                        String[] deadlineDetails = userInput.split("/by");

                        if (deadlineDetails.length == 2) {
                            String description = deadlineDetails[0].stripLeading();
                            String by = deadlineDetails[1].stripLeading();
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
                    userInput = in.nextLine().trim();
                    continue;
                }

                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t" +  tasks[tasksIndex].description);

                System.out.println("\t____________________________________________________________"); 
                tasksIndex++;
            } 
            
            //catch all for invalid inputs
            userInput = in.nextLine().trim();
            continue;

        }
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

}
