public class Error {
    public static void showInvalidCommandError() {
        Printer.printLineSeparator();
        System.out.println("I'm sorry, I do not know what you are trying to say");
        Printer.printLineSeparator();
    }

    public static void showTaskDescriptionError() {
        Printer.printLineSeparator();
        System.out.println("Task description is missing");
        Printer.printLineSeparator();
    }

    public static void showDeadlineFormatError() {
        Printer.printLineSeparator();
        System.out.println("DEADLINE task description is missing \"/by\"");
        Printer.printLineSeparator();
    }

    public static void showEventFormatError() {
        Printer.printLineSeparator();
        System.out.println("EVENT task description is missing \"/at\"");
        Printer.printLineSeparator();
    }

    public static void showDoneFormatError() {
        Printer.printLineSeparator();
        System.out.println("I do not know which task you want me to mark. Give the task number my friend");
        Printer.printLineSeparator();
    }
}
