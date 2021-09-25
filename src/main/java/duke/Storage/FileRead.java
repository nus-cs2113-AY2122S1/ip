package duke.Storage;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;
import duke.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRead extends Storage{

    private File file;
    private TaskList listManager;
    private ArrayList<Task> taskList;

    public FileRead(File file){
        taskList = new ArrayList<>();
        listManager = new TaskList(taskList);
        this.file = file;
    }

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
                listManager.addEvent(splitString[FILE_TASK_DESCRIPTION_POSITION],splitString[FILE_TASK_DETAILS_POSITION], true);
                break;
            case FILE_DEADLINE_STATUS:
                listManager.addDeadline(splitString[FILE_TASK_DETAILS_POSITION],splitString[FILE_TASK_DETAILS_POSITION], true);
            }
            if (splitString[FILE_TASK_COMPLETE_POSITION].equals(FILE_COMPLETE_STATUS)){
                try {
                    listManager.completeTask(taskIndex, true);
                } catch (CommandException e) {
                    e.handleException();
                }
            }
            taskIndex += 1;
        }
        return taskList;
    }
}
