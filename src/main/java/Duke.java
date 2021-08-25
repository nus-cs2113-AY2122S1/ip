import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "        _|       _|_|        _|_|_|      _|      _|    _|_|_|        _|_|_|\n"
                    + "        _|     _|    _|      _|    _|    _|      _|      _|        _|\n"
                    + "        _|     _|_|_|_|      _|_|_|      _|      _|      _|          _|_|\n"
                    + " _|     _|     _|    _|      _|    _|      _|  _|        _|              _|\n"
                    + "  _| _|    _|  _|    _|  _|  _|    _|  _|    _|    _|  _|_|_|  _|  _|_|_|\n";
        System.out.print("Initialising...............\n");
        String line = "--------------------------------------------------------------------------------";
        System.out.println(line + "\n" + logo);
        String byline = "\033[3m----------------------------------Just a rather very intelligent system---------\033[0m\n";

        System.out.println(byline + "Good Evening Sir! I'm J.A.R.V.I.S");
        System.out.println("How may I be of assistance to you today?");
        System.out.println(line + "\n");

        int itemIndex = 0;
        boolean saidBye = false;

        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);

        while (!saidBye) {

            String command = in.nextLine();

            if (command.equals("bye")) {
                saidBye = true;
                System.out.println("Affirmative sir, I'll shut down all operations");
            } else if (command.equals("list")) {
                System.out.println(line + "\n" + "Here are the current tasks in your list:");
                for (int count = 0; count < itemIndex; count++) {
                    System.out.println(count + 1 + "." + tasks[count].getStatusIcon() + " " + tasks[count].getDescription());
                }
                System.out.println(line);
            } else if (command.contains("done")) {
                int taskDoneIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                if (taskDoneIndex > itemIndex - 1 || taskDoneIndex < 0) {
                    System.out.println("Apologies sir but, it seems that task hasn't been created yet :(\n" + line);
                } else {
                    Task taskChosen = tasks[taskDoneIndex];
                    if (taskChosen.isDone()) {
                        System.out.println("Sir I believe this task has already been completed");
                    } else {
                        taskChosen.changeStatusDone(true);
                        System.out.println(line + "\n" + "As you wish sir, this task will be marked as done!");
                    }
                    System.out.println("    " + taskChosen.getStatusIcon() + " " + taskChosen.getDescription() + "\n" + line);
                }
            } else if (command.equals("echo")) {
                System.out.println("What would like me to repeat sir?");
                System.out.println(in.nextLine());
            } else {
                tasks[itemIndex] = new Task(command);
                System.out.println("Will do sir, I've added: " + command);
                itemIndex++;
            }
        }
    }
}