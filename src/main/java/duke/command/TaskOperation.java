package duke.command;

import duke.exception.DukeEmptyParaException;
import duke.exception.DukeException;
import duke.exception.DukeOutOfRangeException;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskOperation {
    // Attributes
    private final ArrayList<Task> tasks; // using Java Collections classes

    public TaskOperation(){
        tasks = new ArrayList<>();
    }

    public void doneTask(String userInput) throws DukeException {
        int i = userInput.indexOf(" ");
        if(i == -1){
            throw new DukeEmptyParaException("There should be an index of task in the done :-(");
        }

        int taskIndex = Integer.parseInt(userInput.substring(i + 1));

        if(taskIndex > tasks.size()){
            throw new DukeOutOfRangeException("Please enter a valid index of task :-(");
        }

        tasks.get(taskIndex - 1).markAsDone();
        System.out.println(Duke.MESSAGE_BOUNDARY);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex - 1));
        System.out.println(Duke.MESSAGE_BOUNDARY);

    }

    public void addTask(String command, String userInput) throws DukeException {
        int i = userInput.indexOf(" ");
        String taskInfo = " ";
        if(i != -1){
            taskInfo = userInput.substring(i + 1);
        }

        switch (command) {
            case "todo":
                tasks.add(ToDos.parse(taskInfo));
                break;
            case "deadline":
                tasks.add(Deadline.parse(taskInfo));
                break;
            case "event":
                tasks.add(Events.parse(taskInfo));
                break;
        }

        System.out.println(Duke.MESSAGE_BOUNDARY + "\n" + "Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + Duke.MESSAGE_BOUNDARY);
    }

    public void deleteTask(String userInput) throws DukeException{
        int i = userInput.indexOf(" ");
        if(i == -1){
            throw new DukeEmptyParaException("There should be an index of task to delete :-(");
        }

        int deleteIndex = Integer.parseInt(userInput.substring(i + 1));

        if(deleteIndex > tasks.size()){
            throw new DukeOutOfRangeException("Please enter a valid index of task :-(");
        }

        System.out.println(Duke.MESSAGE_BOUNDARY);
        System.out.println("Noted. I've removed this task:\n" + tasks.get(deleteIndex - 1));
        tasks.remove(deleteIndex - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(Duke.MESSAGE_BOUNDARY);

    }

    public void loadFile(String filePath) throws FileNotFoundException {
        try{
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while(s.hasNext()){
                loadTask(s.nextLine());
            }
            System.out.println("Loading..." + System.lineSeparator() + "███████████████ 100%");
            printList();
        } catch (FileNotFoundException e){
            System.out.println("OOPS!!! No previous record; File not found :-(");
        }
    }

    public void loadTask(String s){
        String[] taskComponent = s.split(" \\| ");
        char taskType = taskComponent[0].charAt(0);
        int isDone = Integer.parseInt(taskComponent[1].trim());
        String taskInfo = taskComponent[2].trim();
        Task newTask;

        switch (taskType){
            case 'T':
                newTask = new ToDos(taskInfo);
                break;
            case 'D':
                newTask = new Deadline(taskInfo, taskComponent[3].trim());
                break;
            case 'E':
                newTask = new Events(taskInfo, taskComponent[3].trim());
                break;
            default:
                newTask = null;

        }

        if(isDone == 1){
            assert newTask != null;
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

    public void saveFile(String fileName){
        try{
            File f = new File(fileName);
            if(!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            if(!f.exists()){
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(fileName);
            for(Task task : tasks){
                fw.write(task.parseToStore() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("OOPS!!! Something went wrong: " + e.getMessage() + ":-(");
        }
    }

    public void printList(){
        if(tasks.size() == 0){
            System.out.println("No task has added yet!");
        }else{
            System.out.println(Duke.MESSAGE_BOUNDARY + System.lineSeparator() + "Here are the tasks in your list:");
            for(int i=0; i< tasks.size(); i++){
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println(Duke.MESSAGE_BOUNDARY);
        }
    }
}
