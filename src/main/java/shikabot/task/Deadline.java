package shikabot.task;

import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task{

    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public void saveTask() throws IOException {
        FileWriter fw = new FileWriter("data/ShikaTasks.txt", true);
        fw.write("D | " + by + " | " + name + " | " + isDone + "\n");
        fw.close();
    }
}