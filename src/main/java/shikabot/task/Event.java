package shikabot.task;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task{

    private String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public void saveTask() throws IOException {
        FileWriter fw = new FileWriter("data/ShikaTasks.txt", true);
        fw.write("E | " + at + " | " + name + " | " + isDone + "\n");
        fw.close();
    }
}
