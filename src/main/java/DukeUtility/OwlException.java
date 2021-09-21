package DukeUtility;

public class OwlException extends Exception{
    public OwlException(String description) {
        super(description);
    }
    public void printErrorMsg() {
        String description = this.getMessage();
        if(description.equals("invalid task number")) {
            System.out.println("You have keyed in an invalid task number!!!");
        }
        if(description.equals("Invalid 2 part command!!")) {
            System.out.println(description);
        }
        if(description.equals("Task already done!!")) {
            System.out.println(description);
        }
        if(description.equals("Command should not be blank!")) {
            System.out.println(description);
        }
        if(description.equals("The description of todo cannot be empty!")) {
            System.out.println(description);
        }
        if(description.equals("The description of event cannot be empty!")) {
            System.out.println(description);
        }
        if(description.equals("The description of deadline cannot be empty!")) {
            System.out.println(description);
        }
        if(description.equals("The description of done cannot be empty!")) {
            System.out.println(description);
        }
        if(description.equals("Did not specify /by")) {
            System.out.println(description);
        }
        if(description.equals("Did not specify /at")) {
            System.out.println(description);
        }
        if(description.equals("list does not have description!")) {
            System.out.println(description);
        }
        if(description.equals("The description of delete cannot be empty!")) {
            System.out.println(description);
        }
    }

    public static void checkException(String[] inputs) throws OwlException {
        if(inputs[0].isEmpty()) {
            throw new OwlException("Command should not be blank!");
        }
        if(inputs[0].equals("todo")) {
            throw new OwlException("The description of todo cannot be empty!");
        }
        if(inputs[0].equals("done")) {
            throw new OwlException("The description of done cannot be empty!");
        }
        if(inputs[0].equals("event")) {
            throw new OwlException("The description of event cannot be empty!");
        }
        if(inputs[0].equals("deadline")) {
            throw new OwlException("The description of deadline cannot be empty!");
        }
        if(inputs[0].equals("list")) {
            throw new OwlException("list does not have description!");
        }
        if(inputs[0].equals("delete")) {
            throw new OwlException("The description of delete cannot be empty!");
        }
    }
}
