public class Init {
    //greetings
    public static void greet(){
        lineSeparator();
        System.out.println("Hello! I am your memo keeper Duke! \nWhat can I do for you?");
        lineSeparator();
    }

    // repeat
    public static void echo(String userInput){
        lineSeparator();
        System.out.println(userInput);
        lineSeparator();
    }

    //exit
    public static void bye(){
        System.out.println("Bye. Hope to see you again, don't forget to complete your task!");
    }

    private static void lineSeparator() {
        System.out.println("-*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*-");
    }

}
