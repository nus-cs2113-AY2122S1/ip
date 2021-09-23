import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class StorageUI {
    public static Integer phase = 0;

    /**
     * The method calls loadFile method stored in Storage class. Also sets the flag "phase" to
     * integer value 1 only if loadFile can be called so that the welcomeMessage in UI
     * will only prompt the user to load memory if there are no errors in the file
     *
     * It will print the same message when the file Duke.txt has not been created or when the
     * listed information in Duke.txt is stored with incorrect formats, especially the date
     * and time of the deadline task descriptions.
     *
     * @return nil, this is a void method
     * @throws FileNotFoundException if there is no pre-existing Duke.txt file
     * @throws DateTimeParseException if the contents of the existing Duke.txt file does not
     * follow conventions
     * @throws ArrayIndexOutOfBoundsException if the contents of the existing Duke.txt file does not
     * follow conventions
     */
    public static void storageWelcomeMessage(){
        try {
            Storage.loadFile();
            phase = 1;
        } catch (FileNotFoundException e22) {
            System.out.println("File not found! Continue entering commands for automatic file creation!");
        } catch (DateTimeParseException e24) {
            System.out.println(System.lineSeparator() + "Duke.txt failed to load" + System.lineSeparator() +
                    "Kindly check if your Duke.txt existing entries follows new deadline conventions! (eg.YYYY-MM-DD HH:MM)"
                    + System.lineSeparator() + "Loading home screen message..." + System.lineSeparator());
        } catch (ArrayIndexOutOfBoundsException e25) {
            System.out.println(System.lineSeparator() + "Duke.txt failed to load" + System.lineSeparator() +
                    "Kindly check if your Duke.txt existing entries follows new deadline conventions! (eg.YYYY-MM-DD HH:MM)"
                    + System.lineSeparator() + "Loading home screen message..." + System.lineSeparator());
        }
    }
}
