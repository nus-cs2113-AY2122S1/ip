import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String userCommand; //store user input
        Task[] taskList = new Task[100]; //store all the task from user input
        int listSize = 0;
        String logo =
                " ______    __                     __ \n" +
                "/  ____|  |  |                   |__|\n" +
                "|  |      |  |_____    _______    __ \n" +
                "|  |      |   __   \\  /  __   |  |  |\n" +
                "|  |____  |  |  |  |  | |__|  |  |  |\n" +
                "\\______|  |__|  |__|  \\____/\\_|  |__|\n" ;

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hey there! I am Chai!\n" + "What are you doing today?");
        System.out.println("____________________________________________________________");

        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) { //prints list of task
                System.out.println("____________________________________________________________");
                System.out.println("Tasks list so far:");
                for (int i = 0; i < listSize; i++) {
                    System.out.print(taskList[i].getTaskNumber());
                    if (taskList[i].isDone()) { //check if task is done then put a tick if done
                        System.out.print(".[✓] ");
                    }
                    else {
                        System.out.print(".[ ] ");
                    }
                    System.out.println(taskList[i].getTaskName());
                }
                System.out.println("____________________________________________________________");
            }
            else if (userCommand.contains("done ")) { //check user input to see if task completed
                int taskNumberToMark = Integer.parseInt(userCommand.substring(5));
                taskList[taskNumberToMark - 1].markDone();
                System.out.println("____________________________________________________________");
                System.out.println("Well done! Task marked:");
                System.out.println(" [✓] " + taskList[taskNumberToMark - 1].getTaskName());
                System.out.println("____________________________________________________________");
            }
            else {
                System.out.println("____________________________________________________________");
                System.out.println("Added to the list: " + userCommand);
                System.out.println("____________________________________________________________");
                taskList[listSize] = new Task(userCommand); //add new task to taskList
                taskList[listSize].setTaskNumber(listSize + 1); //update the task number
                listSize++;
            }
            userCommand = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("GoodBye!\n");
        System.out.println("____________________________________________________________");
    }
}
