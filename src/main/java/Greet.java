public class Greet {
    public static void miniDuke(String command) {
        switch(command){
        case "bye":
            goodbyeMessage();
            break;
        default:
            System.out.println(command);
        }


    }
    public static void welcomeMessage(){
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void goodbyeMessage(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
