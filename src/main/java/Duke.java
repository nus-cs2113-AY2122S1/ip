import java.util.Scanner;

public class Duke {
    private static Task[] tasks;
    private static int taskCount;

  // prints a string within two horizontal lines, @param is string to be printed
    public static void printWithLines(String text) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public static void printHelloMessage() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

        String helloMessage = "Hello! I'm Duke\n" + "What can I do for you?";

        System.out.println("Hello from\n" + logo);

        printWithLines(helloMessage);
    }

    public static void printByeMessage() {
        String byeMessage = "You've terminated Duke. Have a good day!";
        printWithLines(byeMessage);
     }

    public static void addTask(String task) {

        if (task.startsWith("todo")) {
            tasks[taskCount] = new ToDo(task.replaceFirst("^todo", "").trim());
        } else if (task.startsWith("deadline")) {
              tasks[taskCount] = new Deadline(task.substring(0, task.indexOf("/by"))
                      .replaceFirst("^deadline", "").trim(),
                      task.substring(task.indexOf("/by") + "/by".length()).trim());
        } else if (task.startsWith("event")) {
              tasks[taskCount] = new Event(task.substring(0, task.indexOf("/at"))
                      .replaceFirst("^event", "").trim(),
                      task.substring(task.indexOf("/at") + "/at".length()).trim());
        }

        printWithLines("I've added this task:\n" + tasks[taskCount].toString() + "\n" + "You have " + (taskCount + 1) + " tasks in the list.");
        taskCount++;
    }

    public static void listTasks() {
        String taskList = "Your list of tasks:\n";

        if (taskCount == 0) {
            printWithLines("No tasks listed!");
            return;
        }

        for (int i = 0; i < taskCount; i++) {
            taskList = taskList.concat((i + 1) + ". " + tasks[i].toString() + "\n");
        }

        // erase last newline character
        taskList = taskList.substring(0, taskList.length() - 1);

        printWithLines(taskList);
    }

    public static void setTaskDone(String input) {
        int taskIndexNumber = Integer.parseInt(input.replace("done ", "")) - 1;

        if (taskIndexNumber > taskCount - 1) {
            printWithLines("Task number " + (taskIndexNumber + 1) + " is invalid!\nEnter a valid task number.");
            return;
        }

        Task chosenTask = tasks[taskIndexNumber];
        chosenTask.setDone();
        printWithLines("Task has been marked as done:\n"
            + chosenTask.getStatusIcon()
            + " "
            + chosenTask.description);
    }

  public static void selectCommand(String input) {
      String inputCommand = input.trim().split(" ")[0];
      String inputData = input.replaceFirst(inputCommand, "").trim();

      switch (inputCommand){
      case "todo": case "deadline": case "event":
          addTask(input);
          break;
      case "list":
          listTasks();
          break;
      case "done":
          setTaskDone(inputData);
          break;
      case "bye":
          printByeMessage();
          System.exit(0);
          break;
      default:
          break;
      }
  }

  public static void main(String[] args) {
      tasks = new Task[100];
      taskCount = 0;

      printHelloMessage();

      String line;
      Scanner in = new Scanner(System.in);
      line = in.nextLine();

      while (in.hasNextLine()) {
          selectCommand(line);
          line = in.nextLine();
      }
  }
}
