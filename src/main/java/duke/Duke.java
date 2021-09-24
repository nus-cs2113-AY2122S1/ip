package duke;

import duke.common.Storage;
import duke.exception.EmptyCommandException;
import duke.exception.IllegalCommandException;
import duke.parser.Parser;
import duke.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    private final static String EXIT_COMMAND = "bye";

    /**
     * The main method that initialises and runs Duke until the user exits the program.
     */
    public static void runDuke() {
        try {
            Storage.startDuke();
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            while (!line.equals(EXIT_COMMAND)) {
                Parser.handleCommand(line);
                line = in.nextLine();
            }
            Ui.printByeMessage();
        } catch (EmptyCommandException e) {
            Ui.printEmptyCommandMessage();
        } catch (IllegalCommandException e) {
            Ui.printIllegalCommandMessage();
        } catch (IOException e) {
            Ui.printIOExceptionMessage(e);
        } catch (DateTimeParseException e) {
            Ui.printDateTimeParseMessage();
        }
    }

    public static void main(String[] args) {
        runDuke();
    }
}
