package duke;

import duke.exception.EmptyCommandException;
import duke.exception.IllegalCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void runDuke() {
        Storage.startDuke();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            try {
                Parser.handleCommand(line);
            } catch (EmptyCommandException e) {
                Ui.printEmptyCommandMessage();
            } catch (IllegalCommandException e) {
                Ui.printIllegalCommandMessage();
            } catch (IOException e) {
                Ui.printIOExceptionMessage(e);
            }
            line = in.nextLine();
        }
        Ui.printByeMessage();
    }

    public static void main(String[] args) {
        runDuke();
    }
}
