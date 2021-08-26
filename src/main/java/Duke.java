import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner scan = new Scanner(System.in);
        System.out.println("\tHello from\n" + logo);
        System.out.println("\t_______________________________");
        System.out.println("\tWhy are you here again");
        System.out.println("\tWhat do you want");

        int tasksCount = 0;
        Task[] tasks = new Task[100];

        String input = scan.nextLine();

        // inputs are all case-insensitive
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println("\t_______________________________");
                if (tasksCount > 0) {
                    for (int i = 0; i < tasksCount; i++) {
                        int index = i + 1;
                        System.out.println("\t" + index + ". " + tasks[i].getStatusIcon() + tasks[i].description);
                    }
                    System.out.println("Look at that ever-expanding to-do list. ");
                    System.out.println("You really should stop procrastinating. ");
                } else {
                    System.out.println("There are no tasks in your to-do list. Bet that'll change soon.");
                }
                System.out.println("\t_______________________________");
            } else {
                if (Pattern.matches("(?i)done \\d+", input)) {
                    String number = input.replaceAll("\\D+", "");
                    int n = Integer.parseInt(number);
                    if (n >= 1 && n <= tasksCount) {
                        tasks[n - 1].markAsDone();
                        System.out.println("\t_______________________________");
                        System.out.println("\tWow. You finally completed a task after lazying around all day.");
                        System.out.println("\t[X] " + tasks[n - 1].description);
                    } else {
                        System.out.println("\tError. Task does not exist. Try again.");
                    }
                } else {
                    tasks[tasksCount] = new Task(input);
                    System.out.println("\t_______________________________");
                    System.out.println("\tadded: " + tasks[tasksCount].description);
                    System.out.println("\t_______________________________");
                    tasksCount++;
                }
            }
            input = scan.nextLine();
        }
        System.out.println("\t_______________________________");
        System.out.println("\tbye");
        System.out.println("\t______________________________");
    }
}
