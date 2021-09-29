package duke.task;

import duke.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static final String SEPARATOR_FOR_LOAD = " \\| ";
    static final String SEPARATOR_FOR_SAVE = " | ";
    private String filePath;
    private File f;
    private Ui ui;

    public Storage(String filePath) {
        ui = new Ui();
        this.filePath = filePath;
        f = new File(filePath);
    }

    public ArrayList<String> load() throws IOException {
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] wordsInOneLine = line.split(SEPARATOR_FOR_LOAD);
                for (int i = 0; i < wordsInOneLine.length; i++) {
                    data.add(wordsInOneLine[i]);
                }
            }
        } catch (FileNotFoundException e) {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
        } finally {
            return data;
        }
    }

    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            if (tasks.get(i) instanceof Todo) {
                fw.write("T" + SEPARATOR_FOR_SAVE + tasks.get(i).getisDone()
                        + SEPARATOR_FOR_SAVE + tasks.get(i).getDescription());
            } else if (tasks.get(i) instanceof Deadline) {
                fw.write("D" + SEPARATOR_FOR_SAVE + tasks.get(i).getisDone() +
                        SEPARATOR_FOR_SAVE + tasks.get(i).getDescription()
                        + SEPARATOR_FOR_SAVE + ((Deadline) tasks.get(i)).getBy());
            } else { //the final Task type can only be an event
                fw.write("E" + SEPARATOR_FOR_SAVE + tasks.get(i).getisDone() +
                        SEPARATOR_FOR_SAVE + tasks.get(i).getDescription()
                        + SEPARATOR_FOR_SAVE + ((Event) tasks.get(i)).getAt());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public void save(ArrayList<Task> tasks) {
            try {
                writeToFile(tasks);
            } catch (FileNotFoundException e) {
                ui.showLoadingError(e);
            } catch (IOException e) {
                ui.showLoadingError(e);
            }
    }

}
