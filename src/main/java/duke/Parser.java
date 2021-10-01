package duke;

import duke.command.DukeException;

/**
 * Understand user input. Separate input into command + description, and output to execution.
 */
public class Parser {
    protected String input;
    protected String command;
    protected String description;
    protected Boolean isExit;

    /**
     * command by default = "default"
     * @param input takes user input
     */
    public Parser(String input) {
        this.input = input;
        this.isExit = false;
        this.command = "default";
    }

    /**
     *
     * @return obtain the Exit status
     */
    public Boolean getIsExit(){
        return isExit;
    }

    /**
     * separate user input into command + description
     * @throws DukeException deadline and event inputs must contain /by and /at
     */
    public void parse() throws DukeException {
        if(input.equals("bye")){
            isExit = true;
            command =  "bye";
        }else if(input.equals("list")){
            command =  "list";
        } else if(input.startsWith("done")) {
            command = "done";
            description = input.substring(5).trim();
        }else if(input.startsWith("todo")){
            command =  "todo";
            description = input.substring(5).trim();
        }else if (input.startsWith("deadline")) {
            command =  "deadline";
            description = input.substring(input.indexOf("deadline") + 9).trim();
            if(!description.contains("/by")){
                throw new DukeException("OOPS!!! The description of a deadline must have /by.");
            }
        }else if (input.startsWith("event")) {
            command =  "event";
            description = input.substring(input.indexOf("event") + 6).trim();
            if(!description.contains("/at")){
                throw new DukeException("OOPS!!! The description of a event must have /at.");
            }
        }else if(input.startsWith("delete")){
            command =  "delete";
            description = input.substring(7).trim();
        }else if(input.startsWith("find")){
            command =  "find";
            description = input.substring(5).trim();
        }
    }

    public String getCommand(){
        return command;
    }

    /**
     *
     * @return remove the command word
     * @throws DukeException prevent empty input after command words
     */
    public String getDescription() throws DukeException {
        if(description.isEmpty()){
            throw new DukeException("OOPS!!! The description cannot be empty.");
        }
        return description;
    }

}
