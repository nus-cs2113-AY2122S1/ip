import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> Task = new ArrayList<>();
    private static boolean hasData = false;
    private static StorageFile Store;
    private static ui ui;

    public static void main(String[] args) throws FileNotFoundException {
        String date;
        int count = 0;
        String fileName = "C:\\data\\duke.txt"; //handles data file creation

        Store = new StorageFile(fileName);
        ui = new ui();

        Store.create();
        Store.read();
        ArrayList<String> data = Store.Data;
        hasData = Store.hasData;
        ui.welcome();

        if (hasData) {
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
                    Task.add(count, new Deadline(task, date));
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


        Parser parser = new Parser(Task, count, Store);
        parser.run();
    }
}
//