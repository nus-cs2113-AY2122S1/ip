public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);

        if (message.equals("toDoDescriptionEmptyException")){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else if(message.equals("unrecognisedTask")){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
