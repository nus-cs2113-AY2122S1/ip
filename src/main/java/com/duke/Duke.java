package com.duke;

import com.file.Storage;
import com.task.*;
import com.ui.UI;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Represent a Duke robot that can respond to the user's input, it has <code>storage</code> to store information into PC,
 * <code>tasks</code> to store list of tasks, <code>ui</code> to regulate output to users and <code>status</code> to
 * represent whether the Duke bot is active.
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final UI ui = new UI();
    private boolean status = false;

    /**
     * Constructor of Duke class, initialise Duke by loading information from the storage file and show welcome screen.
     *
     * @param filePath String Object representing the path to storage file
     */
    public Duke(String filePath){
        this.status = true;
        storage = new Storage(filePath);
        tasks = new TaskList();
        ui.welcome();
        loadFromFile();
    }

    /**
     * Load the information stored in the storage file, into TaskList object tasks.
     */
    public void loadFromFile(){
        try {
            ArrayList<String> Lines = storage.readFile();
            for(String line : Lines) {
                if(line.contains("[T]")) {
                    String description = line.substring(6);
                    tasks.addTask(new Todo(description));
                }
                else if (line.contains("[E]")) {
                    String description = line.substring(6, line.indexOf("("));
                    String ddl = line.substring(line.indexOf("(")+1,line.indexOf(")"));
                    tasks.addTask(new Event(description,ddl));
                }else if (line.contains("[D]")) {
                    String description = line.substring(6, line.indexOf("("));
                    String ddl = line.substring(line.indexOf("(")+1,line.indexOf(")"));
                    tasks.addTask(new Deadline(description,ddl));
                }
                tasks.getList().get(tasks.getList().size()-1).setDone(line.contains("[X]"));
            }
        } catch (FileNotFoundException e) {
            ui.print("unable to locate file");
            ui.endLine();
        } catch (IOException e) {
            ui.print("unable to create file");
            ui.endLine();
        }
    }

    /**
     * Terminate the Duke bot, show farewell message and set the status to false
     */
    public void endDuke() {
        ui.print("Bye. Hope to see you again soon!");
        ui.endLine();
        this.status = false;
    }

    /**
     * Duke greets the users by showing welcome message
     */
    public void greet() {
        ui.print("Hello! I'm Duke");
        ui.print("What can I do for you?");
        ui.endLine();
    }

    /**
     * Duke responses to unknown actions
     */
    public void unknownAction() {
        ui.print("Sorry! I don't understand");
        ui.endLine();
    }

    public void find(String item) {
        TaskList matches = new TaskList();
        for(Task i : tasks.getList()){
            if(i.toString().contains(item)) {
                matches.getList().add(i);
            }
        }
        if(matches.getList().size() == 0){
            ui.print("No matches found");
        }
        else{
            ui.print("Here are the matching tasks in your list:");
            listOut(matches);
        }
    }


    /**
     * Add a Task object to the TaskList tasks, and show users the current number of tasks
     *
     * @param item Task object that represents a task item
     */
    public void addList(Task item) {
        tasks.addTask(item);
        ui.print("Got it. I've added this task:");
        ui.print(tasks.getList().get(tasks.getList().size()-1).toString());
        ui.print("Now you have " + tasks.getList().size() + " tasks in the list.");
        try {
            storage.rewriteFile(tasks.getList());
        }catch (IOException e) {
            ui.print("unable to write to file");
        }
        ui.endLine();
    }

    public void listOut(TaskList tasks) {
        if(tasks.getList().size()==0) {
            ui.print("Woohooo no tasks due ~~~~");
        }
        else{
            ui.print("Now you have " + tasks.getList().size() + " tasks in the list.");
        }
        for(int i = 0; i< tasks.getList().size(); i++) {
            ui.print((i+1) + ". "+tasks.getList().get(i).toString());
        }
        ui.endLine();
    }

    /**
     * Mark a specific task as done and update it to the storage file
     *
     * @param line String object that represents the user command
     */
    public void markDone(String line) {
        try {
            line = line.replaceAll("[^(\\d)]", "");
            int index = Integer.parseInt(line);
            index -= 1;
            tasks.getList().get(index).setDone(true);
            ui.print("Congrats! you have done the following task");
            ui.print(tasks.getList().get(index).toString());
            storage.rewriteFile(tasks.getList());
        } catch (NumberFormatException e) {
            ui.print("please enter the index of the task");
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            ui.print("number entered is invalid");
        } catch (IOException e) {
            ui.print("Unable to write files");
            ui.endLine();
        }
        ui.endLine();
    }

    /**
     * delete a specific task from the tasks list and update it to the storage file
     *
     * @param line String object representing the user's command
     */
    public void deleteItem(String line) {
        try {
            line = line.replaceAll("[^(\\d)]", "");
            int index = Integer.parseInt(line);
            index -= 1;
            String deletedItem = tasks.getList().get(index).toString();
            tasks.getList().remove(index);
            storage.rewriteFile(tasks.getList());
            ui.print("I have deleted the following task");
            ui.print(deletedItem);
            ui.print("Now you have " + tasks.getList().size() + " tasks in the list.");
            ui. endLine();
        } catch (NumberFormatException e) {
            ui.print("Please enter the index of the task.");
            listOut(tasks);
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            ui.print("Number entered is invalid.");
            listOut(tasks);
        } catch (IOException e) {
            ui.print("Unable to write files");
            ui.endLine();
        }
    }

    public TaskList getList(){
        return tasks;
    }
  
    /**
     * getter method of status
     *
     * @return status, true representing Duke is active, false the otherwise
     */
    public boolean getStatus() {
        return this.status;
    }
}
