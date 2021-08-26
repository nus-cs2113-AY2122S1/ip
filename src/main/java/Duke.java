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
            //System.out.println((tasks[i].getId() + 1) + ". " +
            //        tasks[i].toString());
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
                      tasks[id].setIsDone();
                      System.out.println("Transcendent, my liege. You have completed:");
                      System.out.println("  " + tasks[id].toString());
                  } else {
                      System.out.println("Have mercy, for that is beyond my knowledge.");
                  }
              } else {
                  System.out.println("You have no tasks, my liege.");
              }

          } else {
              Task newTask = new Task(line);
              tasks[Task.getNumOfTasks() - 1] = newTask;
              printAddItemToList(line);
          }
          InoutputFormatter.printInputStart();
          line = in.nextLine();
          lc = line.toLowerCase();
        }
        System.out.println("Farewell, my liege. Happy hunting!");
    }
}
