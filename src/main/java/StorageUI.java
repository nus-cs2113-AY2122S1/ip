import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class StorageUI {
    public static Integer phase = 0;
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
