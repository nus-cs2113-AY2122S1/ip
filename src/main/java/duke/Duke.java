package duke;
import duke.task.TaskManager;
import duke.userinterface.UserInterface;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import duke.FileIOManager;
public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        try {
            ArrayList<String> tasksString = FileIOManager.read();
            TaskManager.convertTaskStringToTasks(tasksString);
        } catch (IOException e){
            TaskManager.convertTaskStringToTasks(null);
        }

        Greet.openingGreet();
        UserInterface.talkToUser();
        Greet.closingGreet();
    }


}
