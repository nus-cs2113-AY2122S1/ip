package duke.templar;

import duke.exception.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Runs the Duke program until the session is over
 */
public class Duke
{

    public static final String FILE_PATH = "data\\tasks.txt";
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * The main method that runs the Duke program
     * @param args
     * @throws CommandInvalidException
     * @throws DeadlineInvalidFormatException
     * @throws TodoInvalidFormatException
     * @throws EventInvalidFormatException
     * @throws TaskNumberInvalidException
     * @throws NoSuchTaskException
     * @throws IOException
     */
    public static void main(String[] args) throws CommandInvalidException, DeadlineInvalidFormatException, TodoInvalidFormatException,
            EventInvalidFormatException, TaskNumberInvalidException, NoSuchTaskException, IOException
    {
        Ui.printHelloMsg();

        Storage activateStorage = new Storage(FILE_PATH);
        Parser activateParser = activateStorage.loadSave(tasks);

        boolean endSession = false;

        while (!endSession) {

            Ui.printHero();

            String line; // the task
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            
            activateParser.processInput(line, tasks);
            TaskList TL = new TaskList();

            if (line.contains("bye")) {
                activateStorage.writeSave(activateParser);
                Ui.printGoodbyeMsg();
                endSession = true;
            }
        }
    }

}
