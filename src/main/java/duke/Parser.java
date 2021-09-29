package duke;

import duke.command.DukeException;

public class Parser {
    protected String input;
    protected String command;
    protected String description;
    protected String by;
    protected String at;
    protected Boolean isExit;


    public Parser(String input) {
        this.input = input;
        this.isExit = false;
        this.command = "default";
    }
    public Boolean getIsExit(){
        return isExit;
    }

    public void parse() throws DukeException {
        if(input.equals("bye")){
            isExit = true;
            command =  "bye";
        }else if(input.equals("list")){
            command =  "list";
        } else if(input.substring(0, 4).equals("done")) {
            command = "done";
            description = input.substring(5);

        }else if(input.substring(0, 4).equals("todo")){
            command =  "todo";
            description = input.substring(5).trim();
            if(description.isEmpty()){
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        }else if (input.startsWith("deadline")) {
            command =  "deadline";
            description = input.substring(input.indexOf("deadline") + 9).trim();
            if(description.isEmpty()){
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            if(!description.contains("/by")){
                throw new DukeException("OOPS!!! The description of a deadline must have /by.");
            }
        }else if (input.startsWith("event")) {
            command =  "event";
            description = input.substring(input.indexOf("event") + 6).trim();
            if(description.isEmpty()){
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
            if(!description.contains("/at")){
                throw new DukeException("OOPS!!! The description of a event must have /at.");
            }
        }else if(input.startsWith("delete")){
            command =  "delete";
            description = input.substring(7).trim();
        }
    }

    public String getCommand(){
        return command;
    }

    public String getDescription(){
        return description;
    }

    public String getAt() {
        return at;
    }

    public String getBy() {
        return by;
    }
}
