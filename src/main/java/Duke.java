import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        TaskList list = new TaskList();
        String request = in.nextLine();
        while (Request.isBye(request)) {
            try {
                if (Request.isList(request)) {
                    list.printTasks();
                } else if (Request.isDone(request)) { //Should check if it is a task or a command instead
                    list.doneTask(request);
                } else {
                    System.out.println("task read");
                    list.addTask(request);
                }
                request = in.nextLine();
            } catch (Exception ex) {
                System.out.println(ex.getClass());
                if (ex instanceof IncompleteInformationException) {
                    System.out.println(ex.getMessage());
                } else if (ex instanceof InvalidRequestException) {
                    System.out.println("☹ OOPS!!! I can't do that.");
                } else {
                    System.out.println("Some error i don't know of");
                }
                request = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
