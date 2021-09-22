public class Ui {
    public static void welcomeMessage(){
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        if(StorageUI.phase == 1){
            System.out.println("Please type 'list' to access memory!");
        }
    }
}
