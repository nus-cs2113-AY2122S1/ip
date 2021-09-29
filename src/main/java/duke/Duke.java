package duke;

import duke.manager.TaskManager;
import duke.ui.Ui;


public class Duke {

    public static void main(String[] args) {
        Ui.welcomeMessage();
        TaskManager.processInput();
        Ui.byeMessage();
    }
}
