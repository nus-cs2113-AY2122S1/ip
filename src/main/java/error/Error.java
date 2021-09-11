package error;

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
        System.out.println("DEADLINE task description is missing \"/by\" [Format: deadline task description /by deadline time/day/date]");
        Printer.printLineSeparator();
    }

    public static void showEventFormatError() {
        Printer.printLineSeparator();
        System.out.println("EVENT task description is missing \"/at\" [Format: event task description /at event time/day/place]");
        Printer.printLineSeparator();
    }

    public static void showDoneFormatError() {
        Printer.printLineSeparator();
        System.out.println("I do not know which task you want me to MARK. Give the task number my friend");
        Printer.printLineSeparator();
    }

    public static void showDeleteFormatError() {
        Printer.printLineSeparator();
        System.out.println("I do not know which task you want me to DELETE. Give the task number my friend");
        Printer.printLineSeparator();
    }
}
