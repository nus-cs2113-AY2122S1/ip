package duke;

import java.util.Scanner;
//for reading file
import java.io.File;
import java.io.FileNotFoundException;
//for writing to file
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
    }

    public static void printList(Task[] list, int size) {
        int position = 1;
        System.out.println("    Here are the tasks in your list:");
        for( int i = 0 ; i < size ; i ++ ) {
            System.out.println( "    " + position  + "." + list[i]);
            position ++;
        }
    }

    public static void markTaskDone(Task[] list , String word) {
        //convert string number to int number
        int position = Integer.parseInt(word);
        //if task does not exist, return
        if (position > list.length) {
            System.out.println("    Sorry! No such task!");
            return;
        }
        //if task exist, mark task as done
        Task task = list[position - 1];
        task.setDone();
        //print notification
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public static void addTask(Task[] list, Task newTask, int size) {
        //add task to list
        list[size] = newTask;
        //print statements
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + list[size]);
        System.out.println("    Now you have " + (size + 1) + " tasks in the list.");
    }

    //public static Deadlines createNewDeadline(String[] words) {
    public static Deadlines createNewDeadline(String command) {

        String[] words = command.split(" ");
        String by = "";
        String deadlineDescription = "";
        boolean foundBy = false;
        for(int i = 0 ; i < words.length ; i ++) {
            if(foundBy) {
                by = by + " " + words[i];
            } else {
                if(words[i].equals("/by")) {
                    foundBy = true;
                } else {
                    deadlineDescription = deadlineDescription + " " + words[i];
                }
            }
        }
        Deadlines newDeadline = new Deadlines(deadlineDescription.substring(1), by.substring(1));
        return newDeadline;

    }

    public static Events createNewEvent(String command) {

        String[] words = command.split(" ");
        String timeAllocation = "";
        String eventDescription = "";
        boolean foundAt = false;
        for(int i = 0 ; i < words.length ; i ++) {
            if(foundAt) {
                timeAllocation = timeAllocation + " " + words[i];
            } else {
                if(words[i].equals("/at")) {
                    foundAt = true;
                } else {
                    eventDescription =  eventDescription + " " + words[i];
                }
            }
        }
        Events newEvent = new Events(eventDescription.substring(1), timeAllocation.substring(1));
        return newEvent;

    }

    public static int distinguishCommand(String command , Task[] list, int size) {
        //split into word array
        String[] words = command.split(" ");
        String firstWord = words[0];
        int taskCount = size;

        //determine command
        switch (firstWord) {
        case "list":
            printList(list, size);
            break;
        case "done":
            try {
                markTaskDone(list, words[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a done cannot be empty.");
            }
            break;
        case "todo":
            try {
                ToDos newToDo = new ToDos(command.substring(5));
                addTask(list, newToDo, taskCount);
                taskCount ++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                Deadlines newDeadline = createNewDeadline(command.substring(9));
                addTask(list, newDeadline, size);
                taskCount ++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case "event":
            //extract out event description and timeAllocation
            try {
                Events newEvent = createNewEvent(command.substring(6));
                addTask(list, newEvent, size);
                taskCount ++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            }
            break;
        default:
                System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");

            break;
        }

        try{
            writeToFile("data/data.txt",list, taskCount);
        } catch (IOException e) {
            System.out.println("    OOPS!!! There was an error updating the data filelist");
        }
        return taskCount;
    }

    public static int distinguishText(String command , Task[] list, int size) {
        //split into word array
        String[] sections = command.split("[|]");
        String firstSection = sections[0];
        String[] description = sections[2].split(" ");
        int taskCount = size;
        System.out.println(description[0]);

        //create objects
        switch (firstSection) {
        case "T":
            try {
                ToDos newToDo = new ToDos(sections[2]);
                if(sections[1].equals("1")) {
                    newToDo.setDone();
                }
                Duke.addTask(list, newToDo, taskCount);
                taskCount ++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "D":
            try {
                Deadlines newDeadline = createNewDeadline(command.substring(4));
                if(sections[1].equals("1")) {
                    newDeadline.setDone();
                }
                Duke.addTask(list, newDeadline, size);
                taskCount ++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case "E":
            try {
                Events newEvent = createNewEvent(command.substring(4));
                if(sections[1].equals("1")) {
                    newEvent.setDone();
                }
                Duke.addTask(list, newEvent, size);
                taskCount ++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            }
            break;
        default:
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
        return taskCount;
    }

    public static int loadFileContents(String filePath, Task[] list) throws FileNotFoundException {
        int taskCount = 0;
        // create a File for the given file path
        File f = new File(filePath);
        // create a Scanner using the File as the source
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            taskCount = distinguishText(s.nextLine(), list, taskCount);
        }
        return taskCount;
    }

    private static int getDoneNumber( boolean bool) {
        if(bool == true) {
            return 1;
        } else {
            return 0;
        }
    }


    private static void writeToFile(String filePath, Task[] tasks, int size) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for( int i = 0 ; i < size; i ++ ) {
            Task task = tasks[i];
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


    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);

        Task[] list = new Task[100];
        int taskCount = 0;

        //create directory if it does not exist
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //create txt file if it does not exist
        File file = new File("data/data.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //if both directory and file exist read in the data
        if(directory.exists() && file.exists() ) {
            try {
                taskCount = loadFileContents("data/data.txt", list);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }

        showWelcomeMessage();
        line = in.nextLine();

        while (!line.equals("bye")) {
            taskCount = distinguishCommand(line, list, taskCount);
            line = in.nextLine();
        }

        System.out.println("    Bye. Hope to see you again soon!");
    }
}
