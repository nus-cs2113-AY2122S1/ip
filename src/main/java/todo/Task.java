package todo;

import java.util.Scanner;

public class Task {

    private static int currentIndex = 0;
    private static int doneTask = 0;
    private String[] deadline;
    private String[] lineStorage;
    private Boolean[] isDone;
    private String[] type;

    public Task(int listSize) {
        this.lineStorage = new String[listSize];
        this.isDone = new Boolean[listSize];
        this.type = new String[listSize];
        this.deadline = new String[listSize];
    }


    public void addTask(String inputLine) {
        String date;
        String[] stringNoDate = inputLine.split("/", 2);
        String[] storedString = stringNoDate[0].split(" ", 2);
        currentIndex++;
        lineStorage[currentIndex] = storedString[1];
        isDone[currentIndex] = false;
        checkType(storedString[0]);
        date = addDate(inputLine);
        printDivider();
        printAddedTask(date);
        printTaskRemain();
        printDivider();
    }

    private void printAddedTask(String date) {
        System.out.println("Got it. I've added this task:");
        updateDeadline(date);
    }

    public void updateDeadline(String date) {
        if(date == ""){
            deadline[currentIndex] = "";
            System.out.println("[" + type[currentIndex] + "][] " + lineStorage[currentIndex]);
        }else{
            String[] seperatedDate = date.split(" ", 2);
            deadline[currentIndex] = seperatedDate[0] + ": " + seperatedDate[1];
            System.out.println("[" + type[currentIndex] + "][] " + lineStorage[currentIndex] +
                            "(" + deadline[currentIndex] + ")");
        }
    }

    public static void printTaskRemain() {
        System.out.println("Now you have " + (currentIndex - doneTask) + " tasks in the list.");
    }

    public static void printDivider() {
        System.out.println("____________________________________________________________\n");
    }

    public String addDate(String inputLine) {
        String[] words = inputLine.split("/");
        if(inputLine.contains("/")){
            return words[1];
        }else{
            return "";
        }
        
    }
    public void checkType(String inputString) {
        if(inputString.equals("todo")) {
            type[currentIndex] = "T";
        }else if (inputString.equals("deadline")) {
            type[currentIndex] = "D";
        }else if (inputString.equals("event")) {
            type[currentIndex] = "E";
        }else {
            type[currentIndex] = "?";
        }
    }

    public void listTask() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= currentIndex; i++) {
            if (isDone[i] == false) {
                if(deadline[i] == ""){
                    System.out.println(i + ". [" + type[i] + "]" + "[] " + lineStorage[i]);
                }else {
                    System.out.println(i + ". [" + type[i] + "]" + "[] " + lineStorage[i] 
                    + "(" + deadline[i] + ")");
                }
            } else if (isDone[i] == true) {
                if(deadline[i] == ""){
                    System.out.println(i + ". [" + type[i] + "]" + "[X] " + lineStorage[i]);
                }else {
                    System.out.println(i + ". [" + type[i] + "]" + "[X] " + lineStorage[i] 
                    + "(" + deadline[i] + ")");
                }
            }
        }
        printTaskRemain();
        printDivider();
    }

    public void completedTask(String input) {
        printDivider();
        doneTask++;
        String[] words = input.split(" ");
        int index = Integer.parseInt(words[1]);
        isDone[index] = true;
        System.out.println("Nice! I have marked this task as done!");
        if(deadline[index] == ""){
            System.out.println("[" + type[index] + "]" + "[X] " + lineStorage[index]);
        }else {
            System.out.println("[" + type[index] + "]" + "[X] " + lineStorage[index]
            + "(" + deadline[index] + ")");
        }
        printTaskRemain();
        printDivider();
    }

    public Boolean checkWord() {
        Scanner in = new Scanner(System.in);
        String line = null;
        line = in.nextLine();
        assert line != null;
        if (line.contains("done")) {
            completedTask(line);
            return false;
        } else if (line.equals("list")) {
            listTask();
            return false;
        } else if (line.equals("bye")) {
            return true;
        } else {
            addTask(line);
            return false;
        }
    }
}
