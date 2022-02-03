public class InvalidCommandException extends RuntimeException{
    InvalidCommandException() {
        String Message = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println("    ____________________________________________________________");
        System.out.println(Message);
        System.out.println("    ____________________________________________________________");
    }
}
