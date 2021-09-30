package duke;
import java.io.FileNotFoundException;
/*
Duke class is the main class which manages the tasks and the user interface of the program
*/
public class Duke {
    private static Ui ui;
    private static Parser ps;
    public static void main(String[] args) {
        ui=new Ui();
        ps=new Parser();
        ui.greet();
        int endMain=0;
        task tk = new task();
        try{
            tk.readList();
        }
        catch (FileNotFoundException e) {
            ui.fileNotFound();
        }
        while(endMain==0){
            String line;
            line=ui.readCommand();
            if(ps.bye(line)!=-1){
                ui.bye();
                endMain=1;
            }
            else if(ps.list(line)!=-1){
                tk.getList();
            }
            else if(ps.done(line)!=-1){
                int ind=line.indexOf(" ");//.*\\d.*
                tk.setList(Integer.parseInt(line.substring(ind+1)));
            }
            else if(ps.todo(line)!=-1){
                tk.addTodo(line.substring(4));
            }
            else if(ps.deadline(line)!=-1){
                int ind=line.indexOf("/by");
                tk.adddeadLine(line.substring(8,ind-1),line.substring(ind+4));
            }
            else if(ps.event(line)!=-1){
                int ind=line.indexOf("/at");
                tk.addEvent(line.substring(5,ind-1),line.substring(ind+4));
            }
            else if(ps.delete(line)!=-1){
                //int ind=line.indexOf("/at");
                tk.removeTask(line.substring(7));
            }
            else if(ps.find(line)!=-1){
                ui.findWord();
                tk.findTask(line.substring(5));
            }
            else{
                ui.unknownError();
            }
        } 
    }
}
