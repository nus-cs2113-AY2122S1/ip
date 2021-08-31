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
    public String getDescription() {
        String description = "";
        int index;
        if (getCommand().equals("todo")) {
            //find first whitespace and assigns everything up to that point to description variable
            int firstWhitespace = getFirstWhiteSpace(inputString);
            description =  inputString.substring(firstWhitespace).trim();
        } else if (getCommand().equals("list")) {
            description = "";
        } else if (getCommand().equals("event") || getCommand().equals("deadline")) {
            //find first whitespace and first backslash and assign whatever is in the middle to description variable
            int firstWhitespace = getFirstWhiteSpace(inputString);
            int slashPos = getSlash();
            //to include error catching
            if (slashPos != -1) {
                description = inputString.substring(firstWhitespace, slashPos).trim();
            }
        } else if (getCommand().equals("done")) {
            //only the number(in string) remains
            description = inputString.replaceAll("[^0-9]","");
            index = Integer.parseInt(description);
            description = String.valueOf(index);
        } else {
            description = "Invalid command";
        }
        return description;
    }

    /**
     * Returns date of deadline/event.
     *
     * @return date.
     */
    public String getDate() {
        String date = "";
        if (getCommand().equals("deadline") || getCommand().equals(("event"))) {
            int slashPos = getSlash();
            //to include error catching
            if (slashPos != -1) {
                String dateString = inputString.substring(slashPos + 1);
                int firstWhitespace = getFirstWhiteSpace(dateString);
                date = dateString.substring(firstWhitespace);
            }
        } else {
            //date is not relevant for other commands
            date = "";
        }
        return date;
    }
}
