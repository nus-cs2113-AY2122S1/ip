package duke;

/**
 * A class which helps to make sense of user input by separating command details
 */
public class Parser {

    /**
     * Extracts the particular task number from user input
     *
     * @param input User input
     * @return (Index + 1) of task number
     */
    public int getTaskNumber (String input){
        String[] splitString = input.split(" ");
        return Integer.parseInt(splitString[1]);
    }

    /**
     * Extract key to search for from user input
     * @param input User input
     * @return String to search for in task list
     */
    public String getSearchKey (String input){
        return input.substring(5);
    }

    /**
     * Extracts description of events and deadlines from user input
     *
     * @param input User input
     * @param startIndex Starting index to extract substring (9 for deadline, 6 for events)
     * @return Description of event or deadline
     */
    public String getDescription (String input, int startIndex){
        int slash = input.indexOf("/");
        return input.substring(startIndex, slash);

    }

    /**
     * Extract date and time of events and deadlines from user input
     *
     * @param input User input
     * @return Date and time of event or deadline
     */
    public String getDateTime (String input){
        int slash = input.indexOf("/");
        return input.substring(slash + 4);

    }

    /**
     * Extracts details of task from external storage file
     *
     * @param fullText A line in the external storage file
     * @param key String to split description and date and time (by: for deadlines, at: for events)
     * @return Array containing description, date and time
     */
    public String[] extractDetails(String fullText, String key){
        String[] details = new String[2];
        int index = fullText.lastIndexOf(key);
        details[0] = fullText.substring(0, index).trim();
        details[1] = fullText.substring(index + 4, fullText.length() - 1).trim();

        return details;
    }


}
