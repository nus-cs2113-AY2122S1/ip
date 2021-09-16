import duke.Deadline;
import duke.Event;
import duke.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class SaveFile {

    protected String path;

    public SaveFile(String path){
        this.path = path;
    }

    public ArrayList<Task> read() throws IOException{

        File t = new File(this.path);

        ArrayList<Task> taskArrayList = new ArrayList<>();
        if(t.exists()){
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
                            String time = parts[3];
                            taskArrayList.add(new Event(description, time));
                            break;
                        }
                        case "D": {
                            String time = parts[3];
                            taskArrayList.add(new Deadline(description, time));
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
        }
        else{
            t.mkdir();
            t.createNewFile();
        }
        return taskArrayList;
    }

    public void save(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(path);

        StringBuilder txtData = new StringBuilder();

        for (Task t : taskList) {
            if (t instanceof Event) {
                txtData.append("E|").append(t.getStatus()).append("|").append(t.getName()).append("|").append(((Event) t).getTime());
            } else if (t instanceof Deadline) {
                txtData.append("D|").append(t.getStatus()).append("|").append(t.getName()).append("|").append(((Deadline) t).getTime());
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
