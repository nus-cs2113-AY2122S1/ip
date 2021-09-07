public class printMessage {

    public void printIntro() {
        String logo = " _____     _____    _____     _____\n"
                + "|     |   |        |     |   |     |\n"
                + "|_____|   |_____   |_____|   |     |\n"
                + "|         |        |         |     |\n"
                + "|         |_____   |         |_____|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_______________________________________________________");
        System.out.println("Hello! I'm Pepo");
        System.out.println("How can I help you?");
        System.out.println("_______________________________________________________");
    }

    public void printLineBreak() {
        System.out.println("_______________________________________________________");
    }

    public void printListError() {
        System.out.println("Please select a valid task from the list");
    }
}
