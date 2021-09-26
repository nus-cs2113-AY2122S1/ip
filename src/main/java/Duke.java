import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> Data = new ArrayList<>();
    private static ArrayList<Task> Task = new ArrayList<>();
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
        Data = Store.Data;
        hasData = Store.hasData;
        ui.welcome();

        if (hasData) {
            for (int i = 0; i < Data.size(); i++) { //transfers data into task array
                if (Data.get(i).contains("[T]")) {
                    String task = "todo " + Data.get(i).substring(10);
                    Task.add(count, new Todo(task));
                    count++;
                }
                if (Data.get(i).contains("[D]")) {
                    String task = Data.get(i).substring(Data.get(i).lastIndexOf("]") + 1, Data.get(i).lastIndexOf("("));
                    task = "deadline" + task + "/";
                    date = Data.get(i).substring(Data.get(i).lastIndexOf("(") + 1, Data.get(i).lastIndexOf(")"));
                    Task.add(count, new Deadline(task, date));
                    count++;
                }
                if (Data.get(i).contains("[E]")) {
                    String task = Data.get(i).substring(Data.get(i).lastIndexOf("]") + 1, Data.get(i).lastIndexOf("("));
                    task = "event" + task + "/";
                    date = Data.get(i).substring(Data.get(i).lastIndexOf("(") + 1, Data.get(i).lastIndexOf(")"));
                    Task.add(count, new Event(task, date));
                    count++;
                }
                if (Data.get(i).contains("[X]")) {
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