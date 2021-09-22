package shima.command;

import shima.design.Default;
import shima.storage.Storage;

public class ExitProgram {
    public static final String GOODBYE_MSG = "Bye! Hope to see you again :D";

    public static void closeProgram(){
        Default.showMessage(GOODBYE_MSG);
        System.exit(0);
    }
}
