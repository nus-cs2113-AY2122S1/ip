public class IllegalToDoException extends Exception{
    //@Override
    public static void printMessage() {
        System.out.println ("\t____________________________________________________________\n" +
                "\tOOOPS!!! The description of a todo cannot be empty. BIG SADS\n" +
                "\t____________________________________________________________\n\t");
    }
}
