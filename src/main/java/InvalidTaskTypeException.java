public class InvalidTaskTypeException extends Exception {
    public void printExceptionMessage() {
        System.out.println("OOPS!!! I'm sorry, " +
                "but I don't know what that means :-(\n");
    }
}
