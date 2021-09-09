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
            System.out.print(lineBreak);
            try {
                switch (firstWord) {
                case "bye":
                    System.out.println(farewell);
                    return;
                case "list":
                    taskManager.list();
                    break;
                case "done":
                    int taskNumber = Integer.parseInt(command[1]);
                    System.out.println("     Nice! I've marked this task as done: ");
                    taskManager.checkDone(command);
                    System.out.println("       " + taskManager.getName(taskNumber));
                    break;
                case "todo":
                case "deadline":
                case "event":
                    taskManager.add(input);
                    break;
                default:
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("     ☹ OOPS!!! The description of a " + firstWord + " cannot be empty.");
            } catch (NumberFormatException e) {
                System.out.println("     ☹ OOPS!!! The task's index should be an integer.");
            }
            System.out.print(lineBreak);
        }
    }
}
