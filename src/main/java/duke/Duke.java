package duke;

import duke.data.Storage;
import duke.exception.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Storage.load();
        Ui.printWelcomeMessage();
        run();
        Ui.printExitMessage();
    }

    private static void run() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        while (!userInput.equals("bye")) {
            try {
                ArrayList<String> arguments = Parser.parseInput(userInput);
                Parser.handleInput(arguments);
            } catch (DukeCommandException e) {
               Ui.printInvalidCommandMessage();
            } catch (DukeParameterException e) {
                Ui.printParameterErrorMessage();
            } catch (DukeInvalidInputException e) {
                Ui.printInvalidInputMessage();
            } catch (DukeTimeFormatException e) {
                Ui.printTimeParseErrorMessage();
            } catch (DukeTaskNotFoundException e) {
                Ui.printInvalidTaskMessage();
            }
            userInput = input.nextLine();
        }
    }
}
