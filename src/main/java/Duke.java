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

    /**
     * Continuously adds user inputs into list of tasks.
     * Prints list of tasks when user inputs "List" (not case-sensitive),
     * Exits when user inputs "Bye" (not case-sensitive)
     */
    public static void addTask() {
        String[] listOfTasks = new String[100];
        Scanner in = new Scanner(System.in);
        String input;
        boolean hasSaidBye = false;
        int numTasks = 0;
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                hasSaidBye = true;
                exit();
            } else if (input.equalsIgnoreCase("list")) {
                if (numTasks == 0) {
                    printDivider();
                    System.out.println("No tasks yet, add a task now!");
                    printDivider();
                } else {
                    printDivider();
                    for (int i = 1; i <= numTasks; i++) {
                        System.out.println(i + ". " + listOfTasks[i-1]);
                    }
                    printDivider();
                }
            } else {
                listOfTasks[numTasks] = input;
                numTasks++;
                printDivider();
                System.out.println("Added to list: " + input);
                System.out.println("Add another task now or type \"list\" to see current list!");
                printDivider();
            }
        } while (!hasSaidBye);
    }

    public static void main(String[] args) {
      welcome();
      addTask();
    }
}


