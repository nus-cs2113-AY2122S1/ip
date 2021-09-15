package duke;

import java.util.Scanner;

import java.io.IOException;
import java.io.File;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        TaskList list = new TaskList();
        File file = FileHandler.loadFile();
        FileHandler.readFile(list,file);
        String request = in.nextLine();
        while (CommandType.isNotBye(request)) {
            try {
                if (CommandType.isList(request)) {
                    list.printTasks();
                } else if (CommandType.isDone(request)) {
                    list.doneTask(request);
                } else if (CommandType.isDelete(request)) {
                    list.deleteTask(request);
                } else {
                    list.addTask(request);
                }
                FileHandler.writeFile(list,file);
                request = in.nextLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                request = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
