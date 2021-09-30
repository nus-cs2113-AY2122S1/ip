package duke.Storage;

import duke.ErrorHandling.ErrorStaticString;
import duke.task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handle interaction between Duke and text file
 * Create file if file is not found
 * Contain function to write to file
 * Contain function to read from file
 */
public class Storage {

    protected static final String FILE_NAME = "duke.txt";
    protected static final String FILE_CREATED = "File created: ";
    protected static final String FILE_MESSAGE_AT = " at ";
    protected static final String FILE_SEPARATOR = ";";
    protected static final String FILE_TODO_STATUS = "[T]";
    protected static final String FILE_DEADLINE_STATUS = "[D]";
    protected static final String FILE_EVENT_STATUS = "[E]";
    protected static final String FILE_COMPLETE_STATUS = "[X]";
    protected static final String EMPTY_STRING = "";
    protected static final int FILE_TASK_TYPE_POSITION = 0;
    protected static final int FILE_TASK_COMPLETE_POSITION = 1;
    protected static final int FILE_TASK_DESCRIPTION_POSITION = 2;
    protected static final int FILE_TASK_DETAIL_POSITION = 3;

    protected File file;

    /**
     * Constructor for Storage
     * Create duke.txt file if not found
     * Print file name and location if created
     */
    public Storage(){
        try{
            file = new File(FILE_NAME);
            if(file.createNewFile()){
                System.out.println(FILE_CREATED + file.getName() + FILE_MESSAGE_AT + file.getAbsolutePath());
            }
        }catch(IOException e){
            System.out.println(ErrorStaticString.ERROR_FILE_MESSAGE_CREATION);
        }
    }

    /**
     * Load task from text file into list
     *
     * @return list of task from text file
     */
    public ArrayList<Task> load(){
        FileRead fileReader = new FileRead(file);
        return fileReader.convertFileToTask();
    }

    /**
     * Write new task at end of text file
     *
     * @param t Task to write into text file
     */
    public void appendTask(Task t){
        StringBuilder stringBuilder = new StringBuilder();
        FileWrite fileWrite = new FileWrite();
        stringBuilder.append(t.toFile());
        stringBuilder.append(System.lineSeparator());
        String stringToWrite = stringBuilder.toString();
        fileWrite.writeToFile(stringToWrite, true);
    }

    /**
     * Write all tasks in list to text file
     *
     * @param list list of task to get task to write to text file
     */
    public void writeTask(ArrayList<Task> list){
        StringBuilder stringBuilder = new StringBuilder();
        for(Task t: list) {
            stringBuilder.append(t.toFile());
            stringBuilder.append(System.lineSeparator());
        }
        String stringToWrite = stringBuilder.toString();
        FileWrite fileWrite = new FileWrite();
        fileWrite.writeToFile(stringToWrite, false);
    }

    /**
     * Write empty string to text file to clear its content
     */
    public void writeClearTask(){
        try {
            FileWriter fileWriter = new FileWriter(file,false);
            fileWriter.write(EMPTY_STRING);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(ErrorStaticString.ERROR_FILE_MESSAGE_CLEARING);
        }
    }
}
