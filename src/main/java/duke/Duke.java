package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws IOException {

        // Create Task List
        TaskList tasks = new TaskList();

        // Load Tasks
        Storage.loadTasks(tasks);

        // Welcome Message
        Ui.printWelcomeMessage();

        // Active Chat
        activeChat(tasks);

        // Save Tasks
        Storage.saveTasks(tasks);

        // Goodbye Message
        Ui.printGoodbyeMessage();
    }

    private static void activeChat(TaskList tasks) {
        boolean isBye = false;
        String input;
        Scanner in = new Scanner(System.in);
        //Task[] list = new Task[100];

        while(!isBye){
            //store input
            input = in.nextLine();
            //process input
            isBye = Parser.processInput(tasks, input);
        }
    }

}
