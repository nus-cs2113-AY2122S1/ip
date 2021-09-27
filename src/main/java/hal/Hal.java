package hal;

import hal.ui.HalUi;
import hal.util.HalException;
import hal.util.Program;

import java.io.IOException;

import static hal.storage.UserData.initFileWithDirectory;

public class Hal {

    public static final String SAVE_DIRECTORY = "data/";

    /**
     * Main method that continues looping and accepting commands until user inputs 'bye'.
     * @param args input rom the user through CLI.
     */
    public static void main(String[] args) throws IOException {
        initFileWithDirectory(SAVE_DIRECTORY);
        Program currProgram = new Program();
        HalUi newUi = new HalUi();

        newUi.printIntoLogo();
        newUi.printHelpMenu();
        String input;
        while (!currProgram.getCanTerminateHal()) {
            input = newUi.getUserInput();
            try {
                currProgram.parseAndExecuteTask(input);
            } catch (HalException | IOException invalidUserInput) {
                System.out.println(invalidUserInput);
                newUi.printEnterCommandMessage();
            }
        }

        newUi.printGoodbyeMessage();
    }
}
