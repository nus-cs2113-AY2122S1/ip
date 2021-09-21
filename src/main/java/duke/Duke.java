package duke;

import duke.command.Command;
import duke.exception.EmptyCommandException;
import duke.exception.IllegalCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void runDuke() {
        Storage.startDuke();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            try {
                Parser.parseAndExecuteCommand(line);
            } catch (EmptyCommandException e) {
                System.out.println("Sorry, you didn't give me a fitting description for your task.");
            } catch (IllegalCommandException e) {
                System.out.println("That's not a known command format!");
            } catch (IOException e) {
                System.out.println("Something went wrong reading/writing to your data file.");
            }
            line = in.nextLine();
        }
        Command.printByeMessage();
    }

    public static void main(String[] args) {
        runDuke();
    }
}
