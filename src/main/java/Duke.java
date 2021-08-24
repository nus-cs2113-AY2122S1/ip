import java.util.Scanner;

public class Duke {

    public static void greetingsMessage() {
        String helloMessage = "Greetings, I'm DAHNAM. I'm definitely a human, not a machine!\nHow may I help you?\n";
        System.out.println(helloMessage);
    }

    public static void goodbyeMessage() {
        String goodbyeMessage = "Alas, our lovely time together has to come to an end. Au revoir!\n";
        System.out.println(goodbyeMessage);
    }

    public static void denyBotNature() {
        String denyBotNature = "No, I am definitely not a bot. Why do you ask?\n";
        System.out.println(denyBotNature);
    }

    public static void listAllTasks(Task[] taskList) {
        int i = 0;
        while (taskList[i] != null) {
            System.out.println((i + 1) + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].taskDescription);
            i++;
        }
    }

    public static void echoUserInput(Task t) {
        System.out.println("Added: " + t.taskDescription);
    }

    public static void addToTaskList(Task[] taskList, Task t) {
        int i = 0;
        while (taskList[i] != null) {
            i++;
        }
        taskList[i] = t;
    }

    public static void completeTask(Task[] taskList, int i) {
        //Navigate to the given index and change the sign
        String lineBar = "____________________________________________________________\n";
        if ((i < 1) || (taskList[i-1] == null)) {
            System.out.println(lineBar);
            System.out.println("No task found with the given number. Please type 'list' to view your current task "
                    + "list");
            System.out.println(lineBar);
            return;
        }
        taskList[i-1].markAsDone();
        System.out.println(lineBar);
        System.out.println("Bueno! The following task is marked as done: \n[" + taskList[i-1].getStatusIcon() + "] " + taskList[i-1].taskDescription);
        System.out.println(lineBar);
    }

    public static void main(String[] args) {
        String lineBar = "____________________________________________________________\n";
        greetingsMessage();
        Scanner readUserInput = new Scanner(System.in);
        Task[] taskList = new Task[100];
        String userInput;

        while (true) {
            userInput = readUserInput.nextLine();

            if (userInput.contains("done ")) {
                String[] splitInput = userInput.split(" ");
                int taskNumber = Integer.parseInt(splitInput[1]);
                completeTask(taskList, taskNumber);
            }
            else {
                switch (userInput) {
                case "bye":
                    System.out.println(lineBar);
                    goodbyeMessage();
                    System.out.println(lineBar);
                    return;

                case "are you a bot?":
                    System.out.println(lineBar);
                    denyBotNature();
                    System.out.println(lineBar);
                    continue;

                case "list":
                    System.out.println(lineBar);
                    listAllTasks(taskList);
                    System.out.println(lineBar);
                    continue;

                default:
                    System.out.println(lineBar);
                    Task t = new Task(userInput);
                    addToTaskList(taskList, t);
                    echoUserInput(t);
                    System.out.println(lineBar);
                }
            }
        }
    }
}