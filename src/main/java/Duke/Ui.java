package Duke;

public class Ui {

    /**
     * The Class representing all the visual responses by Duke to the user
     */

    public void printIntro() {
        String logo = " _____     _____    _____     _____\n"
                + "|     |   |        |     |   |     |\n"
                + "|_____|   |_____   |_____|   |     |\n"
                + "|         |        |         |     |\n"
                + "|         |_____   |         |_____|\n";
        System.out.println("Salutations from\n" + logo);
        printLineBreak();
        System.out.println("Hello! I'm Pepo");
        System.out.println("How can I help you?");
        printLineBreak();
    }

    public void printLineBreak() {
        System.out.println("____________________________________________________________________________________");
    }

    public void printListError() {
        System.out.println("Oh no! Please select a valid task from the list");
    }

    public void printEmptyDescription() { System.out.println("OOPS! The description cannot be empty"); }

    public void printMissingDeadline() { System.out.println("There seems to be a problem! Please specify a deadline for the task using /by yyyy-mm-dd:tttt"); }

    public void printMissingEvent() { System.out.println("I see an issue here! Please specify a timing for the Event using /at"); }

    public void printIntructionsUnclear() { System.out.println("Instructions Unclear! Type \"help\" for a list of available commands."); }

    public void printMissingKeyword() { System.out.println("Woops! This function requires a keyword");}

    public void printHelp() {
        System.out.println("Hello, here are all the commands available");
        System.out.println("1. \"list\" to view all tasks in the todo list");
        System.out.println("2. \"todo\" to add a task to the todo list");
        System.out.println("3. \"deadline\", task details, \"/by yyyy-mm-dd:tttt\", in order to add a task to the list with a deadline");
        System.out.println("4. \"event\", task details, \"/at\" time, in order to add an event to the list along with the time it occurs.");
        System.out.println("5. \"find\" keyword, in order to find any tasks that contain the keyword within them.");
        System.out.println("6. \"bye\" to exit the program.");
    }
}
