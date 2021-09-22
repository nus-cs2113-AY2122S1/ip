
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
            parseInputFromUser(input, words);
            saveTaskIntoFile(f);
            isConversation = !words[0].equals(Parser.COMMAND_BYE);
        } while (isConversation);
    }

    /**
     * Attempts to write into file
     * @param f File object of the particular pathname
     * @throws IOException when file is missing while app is running
     */
    private static void saveTaskIntoFile(File f) throws IOException {
        try {
            FileWriter fileWrite = new FileWriter(filePath);
            Storage.storeData(fileWrite);
            fileWrite.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found, tasks cannot be saved. Exiting");
            f.createNewFile();
        }   catch (IOException e) {
            System.out.println("Please restart the app and try again");
        }
    }

    /**
     * Takes in input from user and excute based on the command given
     * Also helps to solve exceptions if user enters invalid command
     * @param input user input in String
     * @param words an array of user inputs split by ' '
     */
    private static void parseInputFromUser(String input, String[] words) {
        try {
            Parser.checkCommand(words, input);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
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
    }
}
