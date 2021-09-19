package duke;

//for reading file
import java.io.File;
import java.io.FileNotFoundException;
//for writing to file
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    File directory;
    File file;

    //constructor
    public Storage() {
        //create directory if directory does not exist
        directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //create txt file if file does not exist
        file = new File("data/data.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //methods
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        // create a Scanner using the File object as the source
        Scanner s = null;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (s.hasNext()) {
            distinguishText(s.nextLine(), tasks);
        }
        return tasks;
    }


    public int getDoneNumber( boolean bool) {
        if(bool == true) {
            return 1;
        } else {
            return 0;
        }
    }


    public void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for( int i = 0 ; i < tasks.size(); i ++ ) {
            Task task = tasks.get(i);
            String className = task.getClass().getName();
            String textDescription = "";
            if(className.equals("duke.ToDos")) {
                ToDos todo = (ToDos) task;
                textDescription = "T" + "|" + getDoneNumber(todo.getDone()) + "|" + todo.getDescription();
            } else if ( className.equals("duke.Deadlines")) {
                Deadlines deadline = (Deadlines) task;
                textDescription = "D" + "|" + getDoneNumber(deadline.getDone()) + "|" + task.getDescription()+ " /by " + deadline.by;
            } else if (className.equals("duke.Events") ) {
                Events event = (Events) task;
                textDescription = "E" + "|" + getDoneNumber(event.getDone()) + "|" + task.getDescription() + " /at " + event.timeAllocation;
            }

            fw.write(textDescription +  System.lineSeparator());
            //do not create a string variable by itself with System.lineSeparator()
            System.lineSeparator();
        }
        fw.close();
    }


    public void distinguishText(String command , ArrayList<Task> list) {
        //split into word array
        String[] sections = command.split("[|]");
        String firstSection = sections[0];
        String[] description = sections[2].split(" ");

        //create objects
        switch (firstSection) {
        case "T":
            try {
                ToDos newToDo = new ToDos(sections[2]);
                if(sections[1].equals("1")) {
                    newToDo.setDone();
                }
                //Duke.addTask(list, newToDo);
                list.add(newToDo);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "D":
            try {
                Deadlines newDeadline = TaskList.createNewDeadline(command.substring(4));
                if(sections[1].equals("1")) {
                    newDeadline.setDone();
                }
                list.add(newDeadline);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case "E":
            try {
                Events newEvent = TaskList.createNewEvent(command.substring(4));
                if(sections[1].equals("1")) {
                    newEvent.setDone();
                }
                //Duke.addTask(list, newEvent);
                list.add(newEvent);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            }
            break;
        default:
            System.out.println("    There was an error reading the file");
            break;
        }
    }






}
