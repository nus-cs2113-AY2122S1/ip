package duke;
import java.io.FileNotFoundException;
import static duke.Storage.loadData;

public class Ui {

    public static String line = "------------------------------------------------------------------------------------------\n";

    //Program starts with this greeting
    public static void start() throws DukeException {
        try {
            loadData();
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(line + "Hello! I'm Duke.\n" + logo + "What can i do for you?\n" + line);
        } catch (FileNotFoundException e) {
            System.out.println("Saved file could not be found. I've used one of your wishes to create a new file for you! Thank me later.");
        }
    }


}
