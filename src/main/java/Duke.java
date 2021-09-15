

import java.util.Scanner;
import duke.task.Todo;
import duke.task.Task;
import duke.task.TestTask;
import duke.task.Event;
import duke.task.Deadline;
import duke.exception.DukeException;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    static int listLength;

    static Task task;

    //static Task[] list = new Task[100];
    static String userInput;
    static String command;
    static String description = "No description is needed for this command";
    public static final String SEPARATOR = "-----------------------------------";
    private static ArrayList<Task> tasks;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        //tasks = new ArrayList<>();
        setTasks();

        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("echo")) {
                    echo(userInput);
                } else if (userInput.length() >= 3 && userInput.substring(0, 3).equals("add")) {  // Add the task into the list

                    addTask(userInput);

                } else if (userInput.equals("list")) {      /* Show the list to user which contains indicators to indicate that whether this task is done or have a deadline or is an event with given time */

                    //printList(list);
                    printTasks();

                } else if (userInput.length() >= 4 && userInput.substring(0, 4).equals("done")) {   // Mark the task that needs to be done as done in list

                    markAsDone();

                } else if (userInput.length() >= 4 && userInput.substring(0, 4).equals("todo")) { // Create a Todo object and add it into the task list

                    createTodo();

                } else if (userInput.length() >= 8 && userInput.substring(0, 8).equals("deadline")) {  // Create a deadline object and add it into the task list

                    createDeadline();

                } else if (userInput.length() >= 5 && userInput.substring(0, 5).equals("event")) {  // Create an event object and add it into the task list

                    createEvent();
                }else if (userInput.length() >= 6 && userInput.substring(0,6).equals("delete")){

                    int taskIndex = Integer.parseInt(String.valueOf(userInput.charAt(7)));
                    deleteTask(taskIndex - 1);

                } else {
                    notValidCommand();
                }


            } catch (DukeException e) {
                System.out.println(SEPARATOR);
                System.out.println(e.getMessage());
                System.out.println(SEPARATOR);
            }
            in = new Scanner(System.in);
            userInput = in.nextLine();

            /* Operations that will be taken if user enter "bye" to exit the Duke

             */
            if (userInput.equals("bye")) {
                System.out.println(SEPARATOR);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(SEPARATOR);
            }
        }
    }


        public static void echo(String command){
            while(!command.equals("bye")){
                Scanner in = new Scanner(System.in);
                String userInput;
                userInput = in.nextLine();
                if(!userInput.equals("bye")){
                    System.out.println(SEPARATOR);
                    System.out.println(userInput);
                    System.out.println(SEPARATOR);
                }

            }


        }


        public static void addTask (String userInput) throws DukeException{
            String command = userInput.substring(4);
            description = userInput.substring(4);
            task = new Task(command);
            tasks.add(task);
            //list[listLength] = task;
            System.out.println(SEPARATOR);
            System.out.println("added:" + " " + command);
            System.out.println(SEPARATOR);
            listLength++;
            saveFile();
        }


       public static void printTasks() throws DukeException{
           if(listLength == 0){
               System.out.println(SEPARATOR);
               System.out.println("Hmm...It seems that you have no task now.");
               System.out.println(SEPARATOR);
           }
           else{
               System.out.println(SEPARATOR);
               System.out.println("Here are the tasks in your list:");
               for(int i = 0; i<listLength;i++){
                   System.out.println((i+1) + "." + " " + tasks.get(i));
               }
               System.out.println(SEPARATOR);
           }
       }


        public static void createTodo() throws DukeException{
            if(userInput.length()<= 4){
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            description = userInput.substring(5);
            int currentTaskIndex = listLength;
            command = userInput.substring(5);
            Todo todo = new Todo(command);
            tasks.add(todo);
            //list[currentTaskIndex].setNeedToDo();
            int taskNumber = listLength + 1;
            System.out.println(SEPARATOR);
            System.out.println("Got it. I've added this task:");
            System.out.println(todo);
            System.out.println("Now you have" + " "+ taskNumber + " tasks in the list.");
            System.out.println(SEPARATOR);
            listLength++;
            saveFile();
        }




    public static void createDeadline() throws DukeException{
        if(!userInput.contains("/by") || userInput.length()<= 8){
            throw new DukeException("☹ OOPS!!! The description or the date of a deadline cannot be empty.");
        }
        description = userInput.substring(9);
        int currentTaskIndex = listLength;
        int slashIndex = userInput.indexOf("/");
        int slashByIndex = userInput.indexOf("/by");
        command = userInput.substring(9,slashIndex); //get the description
        String by = userInput.substring(slashByIndex + 4);
        Deadline deadline = new Deadline(command,by);
        tasks.add(deadline);
        listLength++;
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task: ");
        System.out.println(deadline);
        System.out.println("Now you have " + listLength + " tasks in the list");
        System.out.println(SEPARATOR);
        saveFile();
    }



    public static void createEvent() throws DukeException{
        if(!userInput.contains("/at") || userInput.length()<= 5){
            throw new DukeException("☹ OOPS!!! The description or the date of an event cannot be empty.");
        }
        description = userInput.substring(6);
        int currentTaskIndex = listLength;
        int slashIndex = userInput.indexOf("/");
        int slashAtIndex = userInput.indexOf("/at");
        command = userInput.substring(6,slashIndex);
        String at = userInput.substring(slashAtIndex+4);
        Event event = new Event(command,at);
        tasks.add(event);
        //list[currentTaskIndex] = new Event(command,at);
        listLength++;
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task: ");
        System.out.println(event);
        System.out.println("Now you have " + listLength + " tasks in the list");
        System.out.println(SEPARATOR);
        saveFile();
    }

        public static void saveFile(){
            File saveDirection = new File("data");
            saveDirection.mkdir();
            File saveFile = new File(saveDirection,"duke.txt");
            try{
                saveFile.createNewFile();
                FileWriter fileWriter = new FileWriter(saveFile);
                for(int i = 0; i < listLength;i++){
                    char taskType = tasks.get(i).toString().charAt(1);
                    fileWriter.write(taskType + "||" + tasks.get(i).isDone() + "||" + tasks.get(i).getTaskCommand());
                    if (taskType == 'D'){
                        fileWriter.write("||" + ((Deadline) tasks.get(i)).getBy());
                    } else if (taskType == 'E'){
                        fileWriter.write("||" + ((Event) tasks.get(i)).getEventTime());
                    }
                    fileWriter.write("\n");
                }
                fileWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        public static void setTasks(){
             tasks = new ArrayList<Task>();
             listLength = 0;
             openTaskFromFile();
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


        public static void openTaskFromFile(){
            File saveDirection = new File("data");
            saveDirection.mkdir();
            File saveFile = new File(saveDirection,"duke.txt");
            if(saveFile.exists()){
                try{
                    Scanner scanFile = new Scanner(saveFile);
                    while(scanFile.hasNext()){
                        String line = scanFile.nextLine();
                        Task saveTask = createSavedTask(line);
                        tasks.add(saveTask);
                        listLength++;
                    }
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }


      

    public static void markAsDone() throws DukeException {
        if(listLength == 0){
            throw new DukeException("Hmm... It seems that you have no task to mark in your empty list.");
        } else {
            int index;
            index = Integer.parseInt(userInput.substring(5)) - 1;
            tasks.get(index).taskDone();
            System.out.println(SEPARATOR);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(tasks.get(index));
            System.out.println(SEPARATOR);
            saveFile();
        }
    }


    public static void notValidCommand() throws DukeException{
              throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
          }

    public static void deleteTask(int taskIndex) throws DukeException{
        if(userInput.length() <= 6){
            throw new DukeException("☹ OOPS!!! The index of task to be delete cannot be empty.");
        }
        Task deletedTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        listLength--;
        printDeleteMessage(deletedTask);
        saveFile();
    }

    public static void printDeleteMessage(Task deletedTask){
        System.out.println(SEPARATOR);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(deletedTask);
        System.out.println("Now you have " + listLength + " tasks in the list.");
        System.out.println(SEPARATOR);
    }



}
