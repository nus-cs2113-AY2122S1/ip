public class IllegalToDoException extends Exception{
    //@Override
    public static void printMessage() {
        System.out.println ("\t____________________________________________________________\n" +
                "\tOOOPS!!! The description of a todo cannot be empty.\n" +
                "\t____________________________________________________________\n\t");
    }
}
