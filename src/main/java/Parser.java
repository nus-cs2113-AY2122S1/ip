/**
 * Parser class gets the raw input from Logic class and then returns the specified arguments of interest
 * to the Logic class. These are the type of command, the description of the dask and the date.
 *
 * @param "input" raw input from user.
 * @return appropriate information depending on the method called.
 */
public class Parser {
    private String[] arguments;
    private String inputString;

    public Parser(String input) {
        this.inputString = input;
        this.arguments = input.split(" ");
    }

    /**
     * Returns the command type.
     *
     * @return command type.
     */
    public String getCommand() {
        return arguments[0].toLowerCase();
    }

    /**
     * Returns position of the first whitespace encountered in the input.
     *
     * @param input  input from user.
     * @return position of first whitespace.
     */
    public int getFirstWhiteSpace(String input) {
        for (int index = 0; index < input.length(); index++) {
            //sees if character at that index is a whitespace
            if (Character.isWhitespace(input.charAt(index))) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns position of the backslash character used to mark out the border for description and date.
     *
     * @return position of the backslash character.
     */
    public int getSlash() {
        return inputString.indexOf("/");
    }

    /**
     * Returns the description of th task.
     *
     * @return description of the task.
     */
    public String getDescription() throws DukeException{
        String description = "";
        int index;
        switch (getCommand()) {
        case "todo": {
            try {
                //find first whitespace and assigns everything up to that point to description variable
                int firstWhitespace = getFirstWhiteSpace(inputString);
                description = inputString.substring(firstWhitespace).trim();
                if(description.isEmpty()){
                    throw new ToDoCommandError();
                }
                break;
            } catch (Exception e) {
                throw new ToDoCommandError();
            }
        }
        case "list":
            description = "";
            break;
        case "event":
            try {
                //find first whitespace and first backslash and assign whatever is in the middle to description variable
                int firstWhitespace = getFirstWhiteSpace(inputString);
                int slashPos = getSlash();
                //to include error catching
                if (slashPos != -1) {
                    description = inputString.substring(firstWhitespace, slashPos).trim();
                }
                //if description is empty or /at is not being used
                if(description.isEmpty() || !inputString.substring(slashPos+1,slashPos+3).equals("at")){
                    throw new EventCommandError();
                }
                break;
            } catch (Exception e) {
                throw new EventCommandError();
            }
        case "deadline": {
            try {
                //find first whitespace and first backslash and assign whatever is in the middle to description variable
                int firstWhitespace = getFirstWhiteSpace(inputString);
                int slashPos = getSlash();
                if (slashPos != -1) {
                    description = inputString.substring(firstWhitespace, slashPos).trim();
                }
                //if description is empty or /by is not being used
                if(description.isEmpty() || !inputString.substring(slashPos+1,slashPos+3).equals("by")){
                    throw new DeadLineCommandError();
                }
                break;
            } catch (Exception e) {
                throw new DeadLineCommandError();
            }
        }
        case "done":
            try {
                //only the number(in string) remains
                description = inputString.replaceAll("[^0-9]", "");
                index = Integer.parseInt(description);
                description = String.valueOf(index);
                if(description.isEmpty()){
                    throw new DoneCommandError();
                }
                break;
            } catch (Exception e) {
                throw new DoneCommandError();
            }
        //default:
          //  throw new InvalidCommandError();
        }
        return description;
    }

    /**
     * Returns date of deadline/event.
     *
     * @return date.
     */
    public String getDate() throws DukeException{
        String date = "";
        if (getCommand().equals("deadline")) {
            try {
                int slashPos = getSlash();
                //to include error catching
                if (slashPos != -1) {
                    String dateString = inputString.substring(slashPos + 1);
                    int firstWhitespace = getFirstWhiteSpace(dateString);
                    date = dateString.substring(firstWhitespace);
                }
                if(date.isEmpty()){
                    throw new DeadLineCommandError();
                }
            } catch (Exception e) {
                throw new DeadLineCommandError();
            }
        } else if (getCommand().equals(("event"))) {
            try {
                int slashPos = getSlash();
                //to include error catching
                if (slashPos != -1) {
                    String dateString = inputString.substring(slashPos + 1);
                    int firstWhitespace = getFirstWhiteSpace(dateString);
                    date = dateString.substring(firstWhitespace);
                }
                if(date.isEmpty()){
                    throw new EventCommandError();
                }
            } catch (Exception e) {
                throw new EventCommandError();
            }
        } else {
            //date is not relevant for other commands
            date = "";
        }
        return date;
    }
}
