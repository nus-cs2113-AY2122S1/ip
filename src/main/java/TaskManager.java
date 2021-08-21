public class TaskManager {

    public TaskManager() {
    }

    public void greet() {
        String greeting = "****************************\n"
                + "*  ____             ____   *\n"
                + "* |  _ \\    ____   |  _ \\  *\n"
                + "* | |_| |  / __ \\  | |_| | *\n"
                + "* |  _  | | |  | | |  _  | *\n"
                + "* | |_| | | |__| | | |_| | *\n"
                + "* |____/   \\____/  |____/  *\n"
                + "****************************\n"
                + "_________________________________________\n"
                + "Have no fear, Bob is here!\n"
                + "What is it that you require?\n"
                + "_________________________________________\n";
        System.out.println(greeting);
    }

    public void echo(String message) {
        System.out.println("_________________________________________\n" +
                message + "\n_________________________________________\n");
    }

    public void exitMessage() {
        String message = "_________________________________________\n"
                + "Bye. Have a nice day!\n"
                + "_________________________________________\n";
        System.out.println(message);
    }
}
