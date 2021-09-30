package com.command;

import com.duke.Duke;
import com.task.Deadline;
import com.task.Event;
import com.task.Task;
import com.task.Todo;
import java.util.ArrayList;

/**
 * Represents a command given by user, and command Duke to execute the command
 */
public class Command {

    Duke duke;

    private final ArrayList<String> COMMAND_LIST = new ArrayList<>();

    /**
     * Constructor of Command, set the target duke to command
     *
     * @param duke Duke object, the target duke to command
     */
    public Command(Duke duke) {
        this.duke = duke;
        COMMAND_LIST.add("");
    }

    /**
     * Interpret the command given by user and command duke to execute it.
     *
     * @param line String object representing the command given by user
     * @return Returns true if the command is not exit, return false if the command is exit
     */
    public boolean handle(String line){
        line = line.toLowerCase();
        switch(line) {
            case "bye":
            case "end":
                duke.endDuke();
                return false;
            case "hi":
            case "hello":
                duke.greet();
                break;
            case "list":
                duke.listOut(duke.getList());
                break;
            case "clear":
                duke.clear();
                break;
            default:
                handle_task(line);
                break;
        }
        return true;
    }

    public void handle_task(String line){

        if(line.contains("done")) {
            duke.markDone(line);
        }
        else if(line.contains("delete")) {
            duke.deleteItem(line);
        }
        else if(line.contains("todo")) {
            line = line.substring(5);
            duke.addList(new Todo(line));
        }
        else if(line.contains("add")) {
            line = line.substring(4);
            duke.addList(new Todo(line));
        }
        else if(line.contains("deadline") || line.contains("event")) {
            handle_task_time(line);
        }
        else {
            duke.unknownAction();
        }
    }

    public void handle_task_time(String line){
        int SEPARATOR_START_INDEX=0;
        int SEPARATOR_END_INDEX=0;
        int DESCRIPTION_INDEX=0;
        if(line.contains("by")) {
            SEPARATOR_START_INDEX = line.indexOf("by");
            SEPARATOR_END_INDEX= SEPARATOR_START_INDEX+1;
        }
        else if(line.contains("at")) {
            SEPARATOR_START_INDEX = line.indexOf("at");
            SEPARATOR_END_INDEX= SEPARATOR_START_INDEX+1;
        }
        else if(line.contains("@")) {
            SEPARATOR_START_INDEX = line.indexOf("@");
            SEPARATOR_END_INDEX= SEPARATOR_START_INDEX;
        }
        else if(line.contains("/")) {
            SEPARATOR_START_INDEX = line.indexOf("/");
            SEPARATOR_END_INDEX= SEPARATOR_START_INDEX;
        }
        else{
            duke.help();
            return;
        }

        String time = line.substring(SEPARATOR_END_INDEX+1);

        if(line.contains("deadline")) {
            DESCRIPTION_INDEX=9;
            line = line.substring(DESCRIPTION_INDEX,SEPARATOR_START_INDEX);
            duke.addList(new Deadline(line,time));
        }
        else{
            DESCRIPTION_INDEX=6;
            line = line.substring(DESCRIPTION_INDEX,SEPARATOR_START_INDEX);
            duke.addList(new Event(line,time));
        }
    }
}
