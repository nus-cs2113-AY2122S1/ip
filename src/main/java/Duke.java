import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        choose.printHorizontalLine();
        String line = "";
        Scanner in = new Scanner(System.in);

        while (!(line.equals("bye") || line.equals("Bye"))) {
            line = in.nextLine();
            line = line.toLowerCase();

            if (line.equals("list")) {
                choose.list();
            } else if ((line.split(" ")[0].equals("done"))) {
                try {
                    int doneNumber = Integer.parseInt((line.split(" ")[1]));
                    choose.setDone(doneNumber);
                } catch (Exception DukeException) {
                    System.out.println("Oops, please enter which task to be done");
                }

            } else if (line.split(" ")[0].equals("deadline") /*&& line.contains("/by")*/) {
                try {
                    choose.setDeadline(line.split("deadline ")[1], line.split("/by")[1]);
                } catch (Exception DukeException) {
                    System.out.println("Oops please enter /by");
                }

            } else if (line.split(" ")[0].equals("event") /*&& line.contains("/at")*/) {
                try{
                    choose.setEvent(line.split("event ")[1], line.split("/at")[1]);
                } catch(Exception DukeException){
                    System.out.println("Oops please enter /at");
                }

            } else if ((line.split(" ")[0].equals("todo"))) {
                try {
                    choose.setTodo(line.split("todo ")[1]);
                } catch (Exception DukeException) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if ((line.split(" ")[0].equals("delete"))) {
                try {
                    int deleteNumber = Integer.parseInt((line.split(" ")[1]));
                    choose.deleteTask(deleteNumber);
                } catch (Exception DukeException) {
                    System.out.println("Oops please give number to delete");
                }
            }

            else{
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            choose.printHorizontalLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println();
        choose.printHorizontalLine();

    }
}

