package duke;

import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private static final String FILE_NAME = "duke.txt";
    private static final String FILE_CREATED = "File created to store data: ";

    private File file;
    private final ListManager listManager;

    public FileManager(ListManager listManager){
        this.listManager = listManager;
        try{
            file = new File(FILE_NAME);
            if(file.createNewFile()){
                System.out.println(FILE_CREATED + file.getName() + System.lineSeparator() + file.getAbsolutePath());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private ArrayList<String> readFile(){
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void covertStringToTask() {
        ArrayList<String> stringList = readFile();
        int index = 0;
        for (String s : stringList) {
            String[] splitString = s.split(";");
            switch (splitString[0]){
            case "[T]":
                listManager.addTodo(splitString[2], true);
                break;
            case "[E]":
                listManager.addEvent(splitString[2],splitString[3], true);
                break;
            case"[D]":
                listManager.addDeadline(splitString[2],splitString[3], true);
            }
            if (splitString[1].equals("[X]")){
                try {
                    listManager.completeTask(index, true);
                } catch (CommandException e) {
                    e.handleException();
                }
            }
            index += 1;
        }
    }

    public void writeToFile(Task t, boolean isAppend){
        try {
            FileWriter fileWriter = new FileWriter(file, isAppend);
            fileWriter.write(t.toFile());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
