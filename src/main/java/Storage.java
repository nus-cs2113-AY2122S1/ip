import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Storage {
    protected TaskList taskList = new TaskList();
    protected String DIRECTORY_NAME = "data";
    protected String FILE_PATH = "data/duke.txt";
    protected int TASK_INIT_OFFSET = 1;

    public Storage() {
        this.initFolder();
    }

    public void initFolder() {
        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Missing directory, creating new directory.");
        }
    }

    public void initSaveFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner saveFile = new Scanner(f);
        int addCounter = 0;
        while (saveFile.hasNextLine()) {
            String line = saveFile.nextLine();
            String[] processWordsArr = line.split(("[|]"));
            if (line.charAt(0) == 'T') {
                taskList.addTask(new Todo(processWordsArr[2].substring(TASK_INIT_OFFSET)), tasks);
            } else if (line.charAt(0) == 'D') {
                taskList.addTask(new Deadline(processWordsArr[2].substring(TASK_INIT_OFFSET), LocalDate.parse(processWordsArr[3].substring(TASK_INIT_OFFSET))), tasks);
            } else if (line.charAt(0) == 'E') {
                taskList.addTask(new Event(processWordsArr[2].substring(TASK_INIT_OFFSET), LocalDate.parse(processWordsArr[3].substring(TASK_INIT_OFFSET))), tasks);
            }
            if (processWordsArr[1].charAt(1) == '1') {
                tasks.get(addCounter).markAsDone();
            }
            addCounter++;
        }
    }

    public void createSaveFile() throws IOException {
        File f = new File(FILE_PATH);
        f.createNewFile();
        System.out.println("Missing duke.txt, creating new file.");
    }

    public void writeToSave(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < tasks.size(); i++) {

            String doneNumber = "0";
            if (tasks.get(i).isDone) {
                doneNumber = "1";
            }

            String textToAdd;
            if (tasks.get(i).getType().equals("T")) {
                textToAdd = tasks.get(i).getType() + " | "
                        + doneNumber + " | "
                        +  tasks.get(i).description + System.lineSeparator();
            } else {
                textToAdd = tasks.get(i).getType() + " | "
                        + doneNumber + " | "
                        +  tasks.get(i).description + "| " + tasks.get(i).getWhen() + System.lineSeparator();
            }
            fw.write(textToAdd);
        }
        fw.close();
    }
}
