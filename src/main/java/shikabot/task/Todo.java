package shikabot.task;

import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void saveTask() throws IOException {
        FileWriter fw = new FileWriter("data/ShikaTasks.txt", true);
        fw.write("T | null | " + name + " | " + isDone + "\n");
        fw.close();
    }
}
