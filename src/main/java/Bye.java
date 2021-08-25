public class Bye {
    private static String byeCommand = Add.getAddString();
    public static void bye(){
        if(byeCommand.equals("bye")){
            System.out.println();
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}