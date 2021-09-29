package Duke.Storage;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import Duke.DukeException.DukeException;
import Duke.Ui.Ui;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String path;
    private static final int TYPE_POS = 4;
    private static final int TASK_POS = 10;
    private final Ui ui = new Ui();

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Load the task record and copy it into current TaskList.
     *
     * @throws DukeException If file does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(path);
        if (!file.exists()) {
            throw new DukeException("No file can be loaded.");
        }
        BufferedReader reader = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = "";
            Task task = new Task("null");

            while ((tempString = reader.readLine()) != null) {
                if (tempString.charAt(TYPE_POS) == 'T') {
                    task = (new Todo(tempString.substring(TASK_POS)));
                } else if (tempString.charAt(TYPE_POS) == 'E') {
                    String event = tempString.substring(TASK_POS,
                            tempString.indexOf("(") - 1);
                    String at = tempString.substring(tempString.indexOf(":") + 2,
                            tempString.indexOf(")"));
                    task = (new Event(event, at));
                } else if (tempString.charAt(TYPE_POS) == 'D') {
                    String deadline = tempString.substring(TASK_POS,
                            tempString.indexOf("(") - 1);
                    String by = tempString.substring(tempString.indexOf(":") + 2,
                            tempString.indexOf(")"));
                    task = (new Deadline(deadline, by));
                }

                if (tempString.charAt(7) == 'X') {
                    task.markedAsDone();
                }

                tasks.add(task);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (tasks.size() > 0) {
            ui.printSuccessfulLoading();
            ui.showLine();
        } else {
            ui.printListEmptyMessage();
            ui.showLine();
        }
        return tasks;
    }

    /**
     * Save the content of current task list to the txt file.
     *
     * @param tasks Tasklist to be saved.
     */
    public final void save(ArrayList<Task> tasks) {
        BufferedWriter writer = null;
        File file = new File(this.path);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            for (Task task : tasks) {
                int i = 0;
                writer.write(i + 1 + ". " + task.toString());
                i++;
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
