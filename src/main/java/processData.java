import java.util.ArrayList;

public class processData {
    protected String date;
    protected ArrayList<Task> Task;
    protected ArrayList<String> Data;
    protected int count;

    public processData(ArrayList<Task> Task, ArrayList<String> Data, int count) {
        this.Task = Task;
        this.Data = Data;
        this.count = count;
    }

    public void run(){
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
                Task.add(count, new Deadline(task, date, true));
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
    }
}
