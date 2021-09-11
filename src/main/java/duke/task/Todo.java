package duke.task;
import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write("T" + " | " + (isDone ? 1 : 0) + " | " + descr + System.lineSeparator());
        fw.close();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
