package duke;

//for reading file
import java.io.File;
import java.io.FileNotFoundException;
//for writing to file
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from a saved file and saving current tasks into the file
 */
public class Storage {

    File directory;
    File file;

    /**
     * The Constructor method for Storage class
     * It check if there is a file to read data from.
     * If there is no such file, it creates a file in that location
     */
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
    /**
     * This method reads the txt file and loads that data into an ArrayList
     * @return tasks It is an ArrayList consisting of all the tasks in the txt file and their details
     */
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

    /**
     * This method converts a boolean to an integer
     * @param bool Takes in a boolean (true/false)
     * @return An integer representation of that boolean (1/0)
     */
    public int getDoneNumber( boolean bool) {
        if(bool == true) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method writes details of all objects stored in list of Tasks to the txt file
     * @param filePath The location of where the txt file is located
     * @param tasks The list storing the different Tasks the user input
     * @throws IOException If there was a problem writing to the txt file
     */
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
                textDescription = "D" + "|" + getDoneNumber(deadline.getDone()) + "|" + task.getDescription()+ " /by " + deadline.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            } else if (className.equals("duke.Events") ) {
                Events event = (Events) task;
                textDescription = "E" + "|" + getDoneNumber(event.getDone()) + "|" + task.getDescription() + " /at " + event.timeAllocation.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            }

            fw.write(textDescription +  System.lineSeparator());
            System.lineSeparator();
        }
        fw.close();
    }

    /**
     * This method parses a line stored in the txt file and creates the corresponding Todos, Event or Deadline object
     * to be stored in the program's Task ArrayList.
     * @param command A single line from the txt file
     * @param list The list containing the different tasks
     */
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
