package duke;

public class Parser {

    public int getTaskNumber (String input){
        String[] splitString = input.split(" ");
        return Integer.parseInt(splitString[1]);
    }

    public String getSearchKey (String input){
        return input.substring(5);
    }

    public String getDescription (String input, int startIndex){
        int slash = input.indexOf("/");
        return input.substring(startIndex, slash);

    }

    public String getDateTime (String input){
        int slash = input.indexOf("/");
        return input.substring(slash + 4);

    }

    public String[] extractDetails(String fullText, String key){
        String[] details = new String[2];
        int index = fullText.lastIndexOf(key);
        details[0] = fullText.substring(0, index).trim();
        details[1] = fullText.substring(index + 4, fullText.length() - 1).trim();

        return details;
    }


}
