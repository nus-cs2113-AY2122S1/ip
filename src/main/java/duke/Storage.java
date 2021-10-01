package duke;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private static final String FILENAME = "tasklist";

    public static void saveToFile(TaskList tasks) {
        try {
            FileOutputStream fileos = new FileOutputStream(FILENAME);
            ObjectOutputStream objos = new ObjectOutputStream(fileos);
            objos.writeObject(tasks);
        } catch (IOException e) { Ui.printException(e); }
    }

    public static TaskList readFromFile() {
        try {
            FileInputStream fileis = new FileInputStream(FILENAME);
            ObjectInputStream objis = new ObjectInputStream(fileis);
            return (TaskList)objis.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof ClassNotFoundException) {
                Ui.printException(e);
            }
            return new TaskList();
        }
    }
}
