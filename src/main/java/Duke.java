import java.util.Scanner;

public class Duke {
    
    public static void printDivider() {
        System.out.println("________________________________________________________________");
    }
    
    public static void welcome() {
        printDivider();
        System.out.println("Hello! I'm Dude ヽ༼ ・ ل͜ ・ ༽ﾉ");
        System.out.println("Type a task and i'll add it to your list real quick!");
        printDivider();
    }

    public static void exit() {
        System.out.println("________________________________________________________________");
        System.out.println("Bye! Hope to see you again soon! ヽ༼ ͠° ͟ل͜ ͠° ༽ﾉ ");
        printDivider();
    }

    public static void addTask(Task[] listOfTasks, String description) {
        Task t = new Task(description);
        listOfTasks[Task.getNumTasks()] = t; //index 0 in listOfTasks is empty
        printDivider();
        System.out.println("Added to list: " + description);
        System.out.println("Add another task now or type \"list\" to see current list!");
        printDivider();
    }

    public static void printTasks(Task[] listOfTasks) {
        if (Task.getNumTasks() == 0) {
            printDivider();
            System.out.println("No tasks yet, add a task now!");
            printDivider();
        } else {
            printDivider();
            System.out.println("These are your current tasks:");
            for (int i = 1; i <= Task.getNumTasks(); i++) {
                System.out.println(i + ". " + listOfTasks[i].getStatusIcon()
                        + " " + listOfTasks[i].getDescription());
            }
            printDivider();
        }
    }

    public static void markTaskAsDone(Task[] listOfTasks, String input) {
        String[] words = input.split(" ");
        if (words.length == 1) {
            //error if user inputs only "done" with no number behind
            printDivider();
            System.out.println("Invalid format! Please input a task number to be marked as done, " +
                    "in the format \"done X\", where X is the task number!");
            printDivider();
        } else {
            try {
                int taskNum = Integer.parseInt(words[1]);
                if (taskNum > Task.getNumTasks() || taskNum < 1) {
                    //error if user inputs a task number that does not exist
                    printDivider();
                    System.out.println("Please input a valid task number from 1 to " + Task.getNumTasks() + "!");
                    printDivider();
                } else {
                    printDivider();
                    listOfTasks[taskNum].markAsDone();
                    System.out.println(listOfTasks[taskNum].getStatusIcon()
                            + " " + listOfTasks[taskNum].getDescription());
                    printDivider();
                }
            } catch (NumberFormatException e) {
                //error if user did not input a valid integer for task number
                printDivider();
                System.out.println("Invalid format! Please input a task number to be marked as done, " +
                        "in the format \"done X\", where X is the task number!");
                printDivider();
            }
        }
    }

    /**
     * Continuously adds user inputs into list of tasks.
     * Prints list of tasks when user inputs "List" (not case-sensitive),
     * Marks task number X as done when user inputs "done X" (not case-sensitive),
     * Exits when user inputs "Bye" (not case-sensitive)
     */
    public static void addTask() {
        Task[] listOfTasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String input;
        boolean hasSaidBye = false;
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                //exit loop
                hasSaidBye = true;
            } else if (input.equalsIgnoreCase("list")) {
                //print task
                printTasks(listOfTasks);
            } else if (input.toLowerCase().startsWith("done")) {
                //mark as done
                markTaskAsDone(listOfTasks, input);
            } else {
                //add task
                addTask(listOfTasks, input);
            }
        } while (!hasSaidBye);
    }

    public static void main(String[] args) {
      welcome();
      addTask();
      exit();
    }
}


