package duke.Storage;

import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;
import duke.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handle reading from file and
 * Making sense of String from file
 */
public class FileRead extends Storage{

    private final File file;
    private final TaskList listManager;
    private final ArrayList<Task> taskList;

    /**
     * Constructor for FileRead
     * Creates new arraylist to store task from text file in
     * Create Tasklist interact with arraylist after processing string in text file
     *
     * @param file file to read from
     */
    public FileRead(File file){
        taskList = new ArrayList<>();
        listManager = new TaskList(taskList);
        this.file = file;
    }

    /**
     * Read from file and store each line into arraylist
     *
     * @param inputFile file to read from
     * @return list of String from file
     */
    private ArrayList<String> readFile(File inputFile){
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(inputFile);
            while(scanner.hasNext()){
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(ErrorStaticString.ERROR_FILE_MESSAGE_READING);
        }
        return list;
    }

    /**
     * Process String from files into task
     *
     * @return list of task from file
     */
    public ArrayList<Task> convertFileToTask() {
        ArrayList<String> stringList = readFile(file);
        int taskIndex = 0;
        for (String s : stringList) {
            String[] splitString = s.split(FILE_SEPARATOR);
            switch (splitString[FILE_TASK_TYPE_POSITION]){
            case FILE_TODO_STATUS:
                listManager.addTodo(splitString[FILE_TASK_DESCRIPTION_POSITION], true);
                break;
            case FILE_EVENT_STATUS:
                listManager.addEvent(splitString[FILE_TASK_DESCRIPTION_POSITION],splitString[FILE_TASK_DETAIL_POSITION], true);
                break;
            case FILE_DEADLINE_STATUS:
                listManager.addDeadline(splitString[FILE_TASK_DESCRIPTION_POSITION],splitString[FILE_TASK_DETAIL_POSITION],true);
            }
            if (splitString[FILE_TASK_COMPLETE_POSITION].equals(FILE_COMPLETE_STATUS)){
                listManager.completeTask(taskIndex, true);
            }
            taskIndex += 1;
        }
        return taskList;
    }
}
