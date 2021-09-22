import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        choose.printHorizontalLine();
        String line = "";
        Scanner in = new Scanner(System.in);

        try {  // Initialize ./data/duke.txt to store tasks
            choose.initDataStore();
        } catch (IOException e) {
            System.out.println("\t☹ Directory does not exist, error in loading data store.\n" +
                    "Please check if ./data/duke.txt exists.");
        }
        try {  // Load all tasks stored in ./data/duke.txt
            choose.loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("\t☹ File is not found.");
        } catch (duke.DukeException e) {
            System.out.println("\t☹ Invalid file type in data store.");
        }

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
            }
            else{
                if(!(line.split(" ")[0].equals("bye"))){
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");}
            }

            choose.printHorizontalLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        choose.saveTasks();
        System.out.println();
        choose.printHorizontalLine();

    }
}

