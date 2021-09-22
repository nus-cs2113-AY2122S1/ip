
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    public static final String filePath = "duke.txt";

    public static void main(String[] args) throws IOException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "There are too many students changing this logo away.\n"
                + "Let's keep it this way to show the contribution Duke has done to\n"
                + "thousands of students.\n"
                + "F";

        String input;
        System.out.println("Hello from\n" + logo);
        Ui.printWelcomeMessage();
        // load data into arraylist
        //require file
        File f = new File(filePath);
        f.createNewFile();

        // and scanner input
        try {
            Scanner in = new Scanner(f);
            Storage.loadData(in);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        Scanner in = new Scanner(System.in);
        boolean isConversation;// true by default
        do {
            input = in.nextLine();
            String[] words = input.split(" ");
            try {
                Parser.checkCommand(words, input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (UnknownCommandException e) {
                System.out.println(ErrorMessage.EXCEPTION_MESSAGE_UNKNOWN_COMMAND);
            } catch (NumberFormatException e) {
                System.out.println("Please key in an integer");
            } catch (NullPointerException e) {
                System.out.println(ErrorMessage.EXCEPTION_MESSAGE_INPUT_NOT_INT);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid Number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Number keyed in is invalid");
            } catch (IncorrectTimeFormatException e) {
                System.out.println("Date keyed in is of invalid format");
            } catch ( DateTimeParseException e) {
                System.out.println("invalid datetime format");
            } catch ( Exception e) {
                System.out.println("invalid command, please try other commands/inputs");
            }
            try {
                FileWriter fileWrite = new FileWriter(filePath);
                Storage.storeData(fileWrite);
                fileWrite.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found, tasks cannot be saved. Exiting");
            }
            isConversation = !words[0].equals(Parser.COMMAND_BYE);

        } while (isConversation);

    }
}
