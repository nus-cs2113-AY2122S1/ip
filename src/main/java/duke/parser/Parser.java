package duke.parser;

import duke.tasklist.TaskList;
import duke.Ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void task() {
        Scanner in = new Scanner(System.in);
        String inputCommand = in.nextLine();

        while (true) {
            try {
                if (inputCommand.equals("list")) {
                    TaskList.showList(tasks);
                } else if (inputCommand.contains("done")) {
                    TaskList.markAsDone(inputCommand, tasks);
                } else if (inputCommand.contains("todo")) {
                    TaskList.addToDo(inputCommand, tasks);
                } else if (inputCommand.contains("deadline")) {
                    TaskList.addDeadline(inputCommand, tasks);
                } else if (inputCommand.contains("event")) {
                    TaskList.addEvent(inputCommand, tasks);
                } else if (inputCommand.contains("delete")){
                    TaskList.deleteTask(inputCommand, tasks);
                } else if (inputCommand.contains("find")){
                    TaskList.findTask(inputCommand, tasks);
                } else if(inputCommand.contains("bye")){
                    try {
                        Storage.saveData(tasks);
                    } catch (IOException e){
                        System.out.println("Invalid file");
                    }
                    Ui.printBye();
                } else{
                    throw new DukeException();
                }
            } catch (DukeException e) {
                Ui.printHorizontalLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                Ui.printHorizontalLine();
            }
            inputCommand = in.nextLine();
        }
    }
}