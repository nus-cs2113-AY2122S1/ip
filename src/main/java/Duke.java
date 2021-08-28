import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String userCommand; //store user input
        Task[] taskList = new Task[100]; //store all the task from user input
        int listSize = 0;
        int taskNumberToMark;
        String logo =
                " ______    __                     __ \n" +
                "/  ____|  |  |                   |__|\n" +
                "|  |      |  |_____    _______    __ \n" +
                "|  |      |   __   \\  /  __   |  |  |\n" +
                "|  |____  |  |  |  |  | |__|  |  |  |\n" +
                "\\______|  |__|  |__|  \\____/\\_|  |__|\n" ;

        System.out.print(logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hey there! I am Chai!\n" + "What are you doing today?");
        System.out.println("____________________________________________________________");

        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine();

        while (!userCommand.equals("bye")) { //exit command is bye
            if (userCommand.equals("list")) { //prints list of task
                System.out.println("____________________________________________________________");
                System.out.println("Tasks list so far:");
                for (int i = 0; i < listSize; i++) {
                    System.out.print(taskList[i].getTaskNumber());
                    System.out.println('.' + taskList[i].toString());
                }
                System.out.println("____________________________________________________________");
            }
            else if (userCommand.contains("done ")) { //check user input to see if task completed
                taskNumberToMark = Integer.parseInt(userCommand.substring(5));
                taskList[taskNumberToMark - 1].markDone();
                System.out.println("____________________________________________________________");
                System.out.println("Well done! Task marked:");
                System.out.println(" " + taskList[taskNumberToMark - 1].toString());
                System.out.println("____________________________________________________________");
            }
            else {
                if (userCommand.contains("todo")) {
                    taskList[listSize] = new ToDos(userCommand.substring(5));
                }
                else if (userCommand.contains("deadline")) {
                    taskList[listSize] = new Deadlines(userCommand.substring(9, userCommand.indexOf('/') - 1), userCommand.substring(userCommand.indexOf("by") + 3));
                }
                else if (userCommand.contains("event")) {
                    taskList[listSize] = new Events(userCommand.substring(6, userCommand.indexOf('/') - 1), userCommand.substring(userCommand.indexOf("at") + 3));
                }
                System.out.println("____________________________________________________________");
                System.out.println("Got it! Added to the list:");
                System.out.println(" " + taskList[listSize]);
                taskList[listSize].setTaskNumber(listSize + 1); //update the task number
                listSize++; //updates the size of list
                System.out.print("You currently have ");
                System.out.print(listSize);
                System.out.println(" task recorded in your list.");
                System.out.println("____________________________________________________________");
            }
            userCommand = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("GoodBye! Please finish up your task!");
        System.out.println("____________________________________________________________");
    }
}
