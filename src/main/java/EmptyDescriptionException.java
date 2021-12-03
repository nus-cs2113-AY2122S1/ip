public class EmptyDescriptionException extends RuntimeException{
    EmptyDescriptionException() {
        String Message = "     ☹ OOPS!!! The description of a todo cannot be empty.";
        System.out.println("    ____________________________________________________________");
        System.out.println(Message);
        System.out.println("    ____________________________________________________________");
    }
}
