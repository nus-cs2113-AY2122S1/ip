package duke;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        try {
            ArrayList<String> tasksString = Storage.read();
            TaskList.convertTaskStringToTasks(tasksString);
        } catch (IOException e){
            TaskList.convertTaskStringToTasks(null);
        }

        Greet.openingGreet();
        Ui.talkToUser();
        Greet.closingGreet();
    }


}
