import java.util.Scanner;

public class Ui {
    private TaskManager taskManager;
    private Scanner scanner;

    public Ui(TaskManager taskManager, Scanner scanner) {
        this.taskManager = taskManager;
        this.scanner = scanner;
    }

    public void start() {
        String lineBreak = "    ____________________________________________________________\n";
        String logo = "\n" +
                "                                                   \n" +
                "  ,--.       ,------.       ,--.  ,--.      ,--.   \n" +
                ",-'  '-.,---.|  .-.  \\ ,---.|  |  `--',---,-'  '-. \n" +
                "'-.  .-| .-. |  |  \\  | .-. |  |  ,--(  .-'-.  .-' \n" +
                "  |  | ' '-' |  '--'  ' '-' |  '--|  .-'  `)|  |   \n" +
                "  `--'  `---'`-------' `---'`-----`--`----' `--'   \n" +
                "                                                   \n";
        String greetings = lineBreak
                + logo
                + "     Welcome to the toDoList Chatbot\n"
                + "     What would you like to do today?\n"
                + lineBreak;
        String farewell = "     Bye. Hope to see you again soon!";
        System.out.println(greetings);

        while (true) {
            String input = scanner.nextLine();
            String[] command = input.split(" ");
            String firstWord = command[0];
            //Task t = new Task(input);

            System.out.print(lineBreak);
            switch (firstWord) {
            case "bye":
                System.out.println(farewell);
                return;
            case "list":
                System.out.println("     Here are the tasks in your list:");
                taskManager.list();
                break;
            case "done":
                System.out.println("     Nice! I've marked this task as done: ");
                int taskNumber = Integer.parseInt(command[1]);
                taskManager.checkDone(taskNumber);
                System.out.println("       " + taskManager.getName(taskNumber));
                break;

            default:
                taskManager.add(input);
            }
            System.out.print(lineBreak);
        }
    }
}
