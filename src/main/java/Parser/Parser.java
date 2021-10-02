package Parser;

import Tasklist.Tasklist;
import Exception.DukeException;
import Storage.Storage;
import Ui.Ui;
import Task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void parse() {
        Scanner in = new Scanner(System.in);
        String inputTask = in.nextLine();

        while(!inputTask.equals("bye")) {
            try{
                if (inputTask.equals("list")) {
                    Ui.horizontalLine();
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    Ui.horizontalLine();
                } else if (inputTask.contains("done")) {
                    int sep = inputTask.indexOf(" ");
                    int number = Integer.parseInt(inputTask.substring(5));
                    Tasklist.markAsDone(number, tasks);
                } else if (inputTask.contains("todo")) {
                    Tasklist.addToDo(inputTask, tasks);
                } else if (inputTask.contains("deadline")) {
                    Tasklist.addDeadline(inputTask, tasks);
                } else if (inputTask.contains("event")) {
                    Tasklist.addEvent(inputTask, tasks);
                } else if (inputTask.contains("delete")) {
                    Tasklist.deleteTask(inputTask, tasks);
                } //else if (inputTask.contains("find")) {
                    //Tasklist.findTask(inputTask, tasks);
                //}
                else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
                Ui.horizontalLine();
            }
            inputTask = in.nextLine();
        }
        /**if (inputTask.equals("bye")) {
            try {
                Storage.writeToFile(tasks);
            } catch (IOException e) {}
            Ui.horizontalLine();
            System.out.println("Bye. Hope to see you again soon!\n");
        }*/
        in.close();
    }
}
