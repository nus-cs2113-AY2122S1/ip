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
    }
}
