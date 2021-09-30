package duke;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
/*
Task encompases all backend functions of the application
*/
public class task {
    private int mark;
    private String line,description;
    ArrayList<String> namelist=new ArrayList<>();
    ArrayList<Character> donelist=new ArrayList<>();
    ArrayList<Character> statelist=new ArrayList<>();
    private static final Character MARK_AS_DONE = 'X';
    //private static Shape[] shapes = new Shape[100];
    //private static int shapeCount = 0;
    public task(){
        this(0,"null");
    }
    public task(int mark, String line){
        this.mark=mark;
        this.line=line;
    }
/*
getList return all stored content
*/
    public void getList(){
        System.out.println("Here are the tasks in your list:");
        for(int ay=0;ay<namelist.size();ay++){
            System.out.println((ay+1)+". ["+statelist.get(ay)+"]"
            +"["+donelist.get(ay)+"]"+namelist.get(ay));
        }
    }
/*
findTask finds and prints all content which contains user input keyphrase/word
*/
    public void findTask(String line){
        int countfound=0;
        for(int ay=0;ay<namelist.size();ay++){
            if(namelist.get(ay).indexOf(line)!=-1){
                countfound++;
                System.out.println(countfound+". ["+statelist.get(ay)+"]"
                +"["+donelist.get(ay)+"]"+namelist.get(ay));
            } 
        }
    }
/*
setList Marks the corresponding task as completed
*/
    public void setList(int mark){
        this.mark=mark-1;//check
        donelist.set(this.mark, MARK_AS_DONE);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println((this.mark+1)+". ["+statelist.get(this.mark)+"]"
        +"["+donelist.get(this.mark)+"]"+namelist.get(this.mark));
        
    }
/*
setName adds undefined input to list
*/    
    public void setName(String line){
        this.line=line;
        donelist.add(' ');
        statelist.add(' ');
        namelist.add(this.description);
    }
/*
getTasks prints completed task
*/
    public void getTasks(){
        System.out.println("Got it. I've added this task:");
        System.out.println("["+statelist.get(namelist.size()-1)+"]"
        +"["+donelist.get(namelist.size()-1)+"]"+namelist.get(namelist.size()-1));
        System.out.println("Now you have "+ namelist.size() +" tasks in the list.");
        try {
            saveList();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
/*
removeTasks remove task from list
*/
    public void removeTask(String indexString){
        int index=Integer.parseInt(indexString);
        System.out.println("Noted. I've removed this task:");
        System.out.println("["+statelist.get(index-1)+"]"
        +"["+donelist.get(index-1)+"]"+namelist.get(index-1));
        statelist.remove(index-1);
        donelist.remove(index-1);
        namelist.remove(index-1);
        System.out.println("Now you have "+ namelist.size() +" tasks in the list.");
    }
//adds deadline
    public void adddeadLine(String description, String by){
        deadLine ddline=new deadLine(description,by);
        statelist.add('D');
        donelist.add(' ');
        namelist.add(ddline.toString());
        getTasks();
    }
//adds todo task    
    public void addTodo(String description){
        try{
            toDo todo=new toDo(description);
            statelist.add('T');
            donelist.add(' ');
            namelist.add(todo.toString());
            getTasks();
        }
        catch(DukeException e){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
//adds event
    public void addEvent(String description, String by){
        event evenT=new event(description,by);
        statelist.add('E');
        donelist.add(' ');
        namelist.add(evenT.toString());
        getTasks();
    }
//save list to external file
    public void saveList()throws IOException{
        //String path = System.getProperty("user.dir");
        FileWriter fw = new FileWriter("data/duke.txt");
        for(int ay=0;ay<namelist.size();ay++){
            int state;
            if(donelist.get(ay)=='X'){
                state=1;
            }
            else{
                state=0;
            }
            if(namelist.get(ay).indexOf(":")!=-1){
                fw.write(statelist.get(ay)+" | "
                +state+" | "+namelist.get(ay).substring(0,namelist.get(ay).indexOf(":")-4)
                +" |"+namelist.get(ay).substring(namelist.get(ay).indexOf(":")+1,namelist.get(ay).indexOf(")"))+System.lineSeparator());
            }
            else{
                fw.write(statelist.get(ay)+" | "
                +state+" | "+namelist.get(ay)+System.lineSeparator());
            }
        }
        fw.close();
    }
//reads and copies list from external file to task's list
    public void readList() throws FileNotFoundException
    {
        File myObject = new File("data/duke.txt");
        Scanner myReader = new Scanner(myObject);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            statelist.add(data.charAt(0));
            if(data.charAt(4)=='1'){
                donelist.add(MARK_AS_DONE);
            }
            else{
                donelist.add(' ');
            }
            if(data.charAt(0)=='D'){
                data=data.substring(8);
                namelist.add(data.substring(0,data.indexOf("|"))+"by"+data.substring(data.indexOf("|")+1));  
            }
            else if(data.charAt(0)=='E'){
                data=data.substring(8);
                namelist.add(data.substring(0,data.indexOf("|"))+"at"+data.substring(data.indexOf("|")+1));
            }
            else{
                namelist.add(data.substring(8));  
            }
        }
        myReader.close();
    }
}
