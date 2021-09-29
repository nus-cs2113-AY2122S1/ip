package duke;

public class Parser {

    public int getTaskNumber (String input){
        String[] splitString = input.split(" ");
        return Integer.parseInt(splitString[1]);
    }


}
