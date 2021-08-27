package shikabot.saves;

import shikabot.task.Task;
import shikabot.task.Deadline;
import shikabot.task.Event;
import shikabot.task.Todo;
import shikabot.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFile {

    private final String path;
    private final TextUi ui;
    private final File file;
    private ArrayList<Task> tasks = new ArrayList<>();

    public SaveFile(String path) {
        this.path = path;
        this.ui = new TextUi();
        this.file = new File(path);
    }

    /**
     * This function attempts to create the save file at the given path if it does not already exist.
     * @return true if file is created, false otherwise
     */
    public boolean setupSave() {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            ui.printFileErrorMessage();
        } catch (SecurityException e) {
            ui.printSecurityErrorMessage();
        }
        return false;
    }

    /**
     * Function that loads tasks from the given filepath.
     * @throws FileNotFoundException if filepath is invalid
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            loadTask(s.nextLine());
        }
        return tasks;
    }

    /**
     * This function loads tasks by parsing the String inputted, then calling addSavedTask to add it to
     * taskList.
     * @param s String to be parsed.
     */
    private void loadTask(String s) {
        int firstDiv = s.indexOf("|") + 1;
        int secondDiv = s.indexOf("|", firstDiv) + 1;
        int thirdDiv = s.indexOf("|", secondDiv) + 1;
        char type = s.charAt(0);
        String atBy = s.substring(firstDiv, secondDiv - 1).trim();
        String name = s.substring(secondDiv, thirdDiv - 1).trim();
        String done = s.substring(thirdDiv).trim();
        addSavedTask(type, atBy, name, done);
    }

    /**
     * This function directly adds a task to tasks without printing any messages. Used for loading saved tasks.
     * @param type type of task.
     * @param atBy at/by of task, if applicable.
     * @param name name of task.
     * @param done if the task is done or not.
     */
    private void addSavedTask(char type, String atBy, String name, String done) {
        switch(type) {
        case 'T':
            tasks.add(new Todo(name));
            break;
        case 'D':
            tasks.add(new Deadline(name, atBy));
            break;
        case 'E':
            tasks.add(new Event(name, atBy));
            break;
        default:
            return;
        }
        if (done.equals("true")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * This function saves tasks to data/ShikaTasks.txt. It rewrites the txt from scratch.
     * @throws IOException when the saving operation is interrupted.
     */
    public void saveTasks(ArrayList<Task> tasksToSave) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.close();
        for (Task task : tasksToSave) {
            try {
                task.saveTask();
            } catch (IOException e) {
                ui.printSaveErrorMessage();
            }
        }
    }

}
