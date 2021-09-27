package duke;

import duke.manager.TaskManager;
import duke.ui.Ui;

/**
 * TODO : Better Documentation
 *        Update Tests
 *        Add checkstyle
 */

public class Duke {



    public static void main(String[] args) {
        Ui.welcomeMessage();
        TaskManager.processInput();
        Ui.byeMessage();
    }




}
