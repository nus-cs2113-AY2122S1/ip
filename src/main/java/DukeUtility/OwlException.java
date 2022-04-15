package DukeUtility;


public class OwlException extends Exception{
    public OwlException(String description) {
        super(description);
    }

    /**
     * Prints the OwlException's input message given when it was thrown.
     */
    public void printErrorMsg() {
        String description = this.getMessage();
        System.out.println(description);
    }

    /**
     * Throws an OwlException with the specific error message respective to each wrong command given as input.
     * 
     * @param inputs An array representing the different parts of the user input.
     * @throws OwlException If it is an invalid command.
     */
    public static void checkException(String[] inputs) throws OwlException {
        if(inputs[0].isEmpty()) {
            throw new OwlException("Command should not be blank!");
        } else if(inputs[0].equals("todo")) {
            throw new OwlException("The description of todo cannot be empty!");
        } else if(inputs[0].equals("done")) {
            throw new OwlException("The description of done cannot be empty!");
        } else if(inputs[0].equals("find")) {
            throw new OwlException("The description of find cannot be empty!");
        } else if(inputs[0].equals("event")) {
            throw new OwlException("The description of event cannot be empty!");
        } else if(inputs[0].equals("deadline")) {
            throw new OwlException("The description of deadline cannot be empty!");
        } else if(inputs[0].equals("list")) {
            throw new OwlException("list does not have description!");
        } else if(inputs[0].equals("delete")) {
            throw new OwlException("The description of delete cannot be empty!");
        }
    }
}
