package duke;

import duke.ui.HalUi;
import duke.util.HalException;
import duke.util.Program;

import java.io.IOException;


import static duke.storage.UserData.initFileWithDirectory;

public class Duke {

    public static void main(String[] args) throws IOException {
        Program currProgram = new Program();
        HalUi newUi = new HalUi();

        newUi.printIntoLogo();
        newUi.printHelpMenu();
        String input;
        while (!currProgram.getCanTerminateHal()) {
            input = newUi.getUserInput();
            try {
                initFileWithDirectory("data/");
                currProgram.parseAndExecuteTask(input);
            } catch (HalException | IOException invalidUserInput) {
                System.out.println(invalidUserInput);
                newUi.printEnterCommandMessage();
            }
        }

        newUi.printGoodbyeMessage();
    }
}
