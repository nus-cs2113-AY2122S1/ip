import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The Duke program performs the functions of a todolist application. It is capable of adding new tasks in the form of
 * todos, deadlines or events, marking them as done, listing them, finding tasks based on keywords and removing
 * specific tasks.
 */
public class Duke {
    private static final ArrayList<Task> Task = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        String date;
        int count = 0; //holds number of Tasks in Task array
        String fileName = "C:\\data\\duke.txt"; //file path

        StorageFile store = new StorageFile(fileName); //creates new StorageFile object to handle functions related to
        // the data file
        ui ui = new ui(); //creates new ui object to handle printing of ui messages

        store.create(); //creates new data text file
        store.read(); //reads existing data in file
        ArrayList<String> data = store.Data; //assigns scanned data from StorageFile program to String Array data in main
        boolean hasData = store.hasData;
        ui.welcome();

        if (hasData) { //converts Data from string to Task by adding todos, deadlines and events to Task array

            for (int i = 0; i < data.size(); i++) { //transfers data into task array
                if (data.get(i).contains("[T]")) {
                    String task = "todo " + data.get(i).substring(10);
                    Task.add(count, new Todo(task));
                    count++;
                }
                if (data.get(i).contains("[D]")) {
                    String task = data.get(i).substring(data.get(i).lastIndexOf("]") + 1, data.get(i).lastIndexOf("("));
                    task = "deadline" + task + "/";

                    date = data.get(i).substring(data.get(i).lastIndexOf("(") + 1, data.get(i).lastIndexOf(")"));
                    Task.add(count, new Deadline(task, date, true));
                    count++;
                }
                if (data.get(i).contains("[E]")) {
                    String task = data.get(i).substring(data.get(i).lastIndexOf("]") + 1, data.get(i).lastIndexOf("("));
                    task = "event" + task + "/";
                    date = data.get(i).substring(data.get(i).lastIndexOf("(") + 1, data.get(i).lastIndexOf(")"));
                    Task.add(count, new Event(task, date));
                    count++;
                }
                if (data.get(i).contains("[X]")) {
                Task.get(i).setDone();
                }
           }
            ui.listExists();
        }

        Parser parser = new Parser(Task, count, store);
        parser.run();
    }
}
