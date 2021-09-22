package duke;

import duke.processes.Interface;
import duke.processes.Manager;
import duke.processes.ProcessFiles;
import duke.processes.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static Manager manager = new Manager(taskList);

    public static void main(String[] args) {
        Interface.introductoryMessage();
        ProcessFiles.LoadTasks();
        Duke.runIkaros();
        ProcessFiles.SaveTasks();
        Interface.goodbyeMessage();
    }

    /**
     * Runs Ikaros, manages the instructions of ikaros
     */
    protected static void runIkaros() {
        boolean isRunning = true;

        Scanner in = new Scanner(System.in);
        String response;
        String[] command;

        while (isRunning) {
            response = in.nextLine();
            Interface.lineBreak();
            command = response.split(" ", 10);
            switch (command[0]) {
            case "echo":
                Manager.echo();
                break;
            case "list":
                Manager.printList();
                break;
            case "done":
            case "undo":
                Manager.manageDoUndo(command);
                break;
            case "remove":
            case "add":
                Manager.manageInstructions(command, response);
                break;
            case "bye":
                isRunning = false;
                break;
            case "help":
                Manager.help();
                break;
            default:
                System.out.println("I didn't catch that!");
                Interface.lineBreak();
                Manager.help();
                break;
            }
        }
    }
}