package duke.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.*;
import duke.task.TaskList;

import duke.Ui.Ui;


public class Storage {

    public Storage(){

    }

    public static void saveFile(TaskList tasks){
        File saveDirection = new File("data");
        saveDirection.mkdir();
        File saveFile = new File(saveDirection,"duke.txt");
        try{
            saveFile.createNewFile();
            FileWriter fileWriter = new FileWriter(saveFile);
            for(int i = 0; i < tasks.getSize();i++){
                char taskType = tasks.getIndexTask(i).toString().charAt(1);
                fileWriter.write(taskType + "||" + tasks.getIndexTask(i).isDone() + "||" + tasks.getIndexTask(i).getTaskCommand());
                if (taskType == 'D'){
                    fileWriter.write("||" + ((Deadline) tasks.getIndexTask(i)).getBy());
                } else if (taskType == 'E'){
                    fileWriter.write("||" + ((Event) tasks.getIndexTask(i)).getEventTime());
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void openTaskFromFile(TaskList tasks){
        File saveDirection = new File("data");
        saveDirection.mkdir();
        File saveFile = new File(saveDirection,"duke.txt");
        if(saveFile.exists()){
            try{
                Scanner scanFile = new Scanner(saveFile);
                while(scanFile.hasNext()){
                    String line = scanFile.nextLine();
                    Task saveTask = createSavedTask(line);
                    tasks.openTask(saveTask);
                    //(Duke.listLength)++;
                }
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public static Task createSavedTask(String line){
        String[] lineDivision = line.split("\\|\\|");
        Task saveTask;
        if(lineDivision[0].equals("D")){

            saveTask = new Deadline(lineDivision[2],lineDivision[3],Boolean.parseBoolean(lineDivision[1]));

        } else if (lineDivision[0].equals("E")){

            saveTask = new Event(lineDivision[2],lineDivision[3],Boolean.parseBoolean(lineDivision[1]));

        } else {

            saveTask = new Todo(lineDivision[2],Boolean.parseBoolean(lineDivision[1]));

        }
        return saveTask;
    }



}
