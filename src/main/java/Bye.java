public class Bye {
    private static String byeCommand = Echo.getCommands();
    public static void bye(){
        if(byeCommand.equals("bye")){
            System.out.println("\n");
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
