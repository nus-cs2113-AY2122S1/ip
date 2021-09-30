package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Stores all data into a formatted text file.
 */
public class Storage {

    protected String path;

    public Storage(String path){
        this.path = path;
    }
    /**
     * Returns a list of saved tasks from "data.txt"
     * Creates a file according to the file path if file is not found.
     * Throws IOException if an error occurs.
     */
    public TaskList read() throws IOException{

        File t = new File(this.path);
        TaskList taskArrayList = new TaskList();
        if(!t.exists()){
            File dir = new File("data");
            dir.mkdir();
            t.createNewFile();
        }
            try{
                Scanner scanner = new Scanner(t);
                int index = 0;
                while(scanner.hasNext()){
                    String[] parts = scanner.nextLine().split("\\|");
                    String type = parts[0];
                    String status = parts[1];
                    String description = parts[2];
                    switch (type) {
                        case "T":
                            taskArrayList.add(new Task(description));
                            break;
                        case "E": {
                            String date = parts[3];
                            taskArrayList.add(new Event(description, date, parts[4]));
                            break;
                        }
                        case "D": {
                            taskArrayList.add(new Deadline(description, parts[3], parts[4]));
                            break;
                        }
                    }
                    if(status.equals("X")){
                        taskArrayList.get(index).taskDone();
                    }
                    index++;
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

        return taskArrayList;
    }
    /**
     * Writes the contents of the list of Tasks in Duke to a .txt file "data.txt"
     * The data is stored as a formatted text file.
     *
     * @param taskList list of Tasks in Duke to be stored.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path);

        StringBuilder txtData = new StringBuilder();

        for (int i = 0; i < taskList.size() ;i++) {
            Task t = taskList.get(i);
            if (t instanceof Event) {
                txtData.append("E|").append(t.getStatus()).append("|").append(t.getName()).append("|")
                        .append(((Event) t).getDates()).append("|").append(((Event) t).getTime());
            } else if (t instanceof Deadline) {
                txtData.append("D|").append(t.getStatus()).append("|").append(t.getName()).append("|")
                        .append(((Deadline) t).getDates()).append("|").append(((Deadline) t).getTime());
            } else {
                txtData.append("T|").append(t.getStatus()).append("|").append(t.getName());
            }
            txtData.append("\n");
        }

        try{
            fw.write(txtData.toString());
            fw.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

}
