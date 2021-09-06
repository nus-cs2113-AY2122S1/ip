public class DukeException extends Exception {
    public static String openingString = "     ☹ OOPS!!! ";

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public void printErrorMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println(openingString + this.getMessage());
        System.out.println("    ____________________________________________________________");
    }

}
