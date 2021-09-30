package Duke;
import java.io.IOException;

public class Parser {
    public static final String Line = "    ____________________________________________________________";
    public static boolean flag;

    public static boolean parse(String request){
        System.out.println(Line);
        if(request.contains("done")){
            Command.done(request);
            flag = true;
        }
        else if(request.contains("delete")){
            Command.delete(request);
            flag = true;
        }
        else if(request.contains("todo")){
            Command.todo(request);
            flag = true;
        }
        else if(request.contains("deadline")){
            Command.deadline(request);
            flag = true;
        }
        else if(request.contains("event")){
            Command.event(request);
            flag = true;
        }
        else if(request.contains("find")){
            Command.find(request);
            flag = true;
        }
        else if(request.contains("list")) {
            Command.printList();
            flag = true;
        }
        else if(request.contains("bye")){
            Command.bye();
            flag = false;
        }
        else{
            Command.printerror();
            flag = true;
        }
        System.out.println(Line);
        String f = "lines.txt";
            try {
                Command.writeToFile(f);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        return flag;
    }
}