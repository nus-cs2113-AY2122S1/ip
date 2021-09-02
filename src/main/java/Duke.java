import java.util.Scanner;

public class Duke {
    
    public static void echoUserInput(String input) {
        InoutputFormatter.printOutputSeparator();
        InoutputFormatter.printInputStart();
        System.out.println(input);
        InoutputFormatter.printOutputSeparator();
    }

    public static void printAddItemToList(String item) {
        InoutputFormatter.printOutputSeparator();
        InoutputFormatter.printOutputStart();
        System.out.println("As you command. Added: " + item);
        InoutputFormatter.printOutputSeparator();
    }

    public static void printFormattedTasks(Task[] tasks) {
        InoutputFormatter.printOutputSeparator();
        System.out.println("Your magnificent tasks:");
        for (int i = 0; i < tasks.length && tasks[i] != null; i++) {
            InoutputFormatter.printOutputStart();
            System.out.println(tasks[i].toString());
        }
        InoutputFormatter.printOutputSeparator();
    }

    public static void main(String[] args) {
        String line;
        String lc; //lowercase line
        Task[] tasks = new Task[100];

        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you, my liege?");

        InoutputFormatter.printInputStart();
        line = in.nextLine();
        lc = line.toLowerCase();
        
        while (!lc.equals("bye")) {
          if (lc.equals("list")) {
              printFormattedTasks(tasks);
          } else if (lc.contains("done") && lc.substring(0, 4).equals("done")) {
              if (Task.getNumOfTasks() > 0) {
                  String inputNumStr = lc.replace("done", "").trim();
                  int inputNum = Integer.parseInt(inputNumStr);
                  if (inputNum > 0 && inputNum <= Task.getNumOfTasks()) {
                      int id = inputNum - 1;
                      tasks[id].setDone();
                      System.out.println("Transcendent, my liege. You have completed:");
                      System.out.println("  " + tasks[id].toString());
                  } else {
                      System.out.println("Have mercy, for that is beyond my knowledge.");
                  }
              } else {
                  System.out.println("You have no tasks, my liege.");
              }

          } else if (lc.contains("todo") && lc.substring(0, 4).equals("todo")) {
              Todo newTodo = new Todo(line);
              tasks[Task.getNumOfTasks() - 1] = newTodo;
              printAddItemToList(newTodo.toString());
          } else if (lc.contains("deadline") && lc.substring(0, 8).equals("deadline")) {
              if (!lc.contains("/by")) {
                  System.out.println("By when, my liege?");
              } else {
                  int dividerPosition = lc.indexOf("/by");
                  String description = line.substring(9, dividerPosition).trim();
                  String by = line.substring(dividerPosition + 3).trim();
                  Deadline newDeadline = new Deadline(description, by);
                  tasks[Task.getNumOfTasks() - 1] = newDeadline;
                  printAddItemToList(newDeadline.toString());
              }
          } else if (lc.contains("event") && lc.substring(0, 5).equals("event")) {
              if (!lc.contains("/at")) {
                  System.out.println("Where or when is this event, my liege?");
              } else {
                  int dividerPosition = lc.indexOf("/at");
                  String description = line.substring(6, dividerPosition).trim();
                  String at = line.substring(dividerPosition + 3).trim();
                  Event newEvent = new Event(description, at);
                  tasks[Task.getNumOfTasks() - 1] = newEvent;
                  printAddItemToList(newEvent.toString());
              }
          } else {
                System.out.println("I do not comprehend your command, my liege.");
            }
          InoutputFormatter.printInputStart();
          line = in.nextLine();
          lc = line.toLowerCase();
        }
        System.out.println("Farewell, my liege. Happy hunting!");
    }
}
