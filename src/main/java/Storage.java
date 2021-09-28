import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Storage{

    TaskList taskList;
    static Parser parser;

    public Storage(TaskList taskList, Parser parser) {
        this.taskList = taskList;
        this.parser = parser;
    }

    /**
     * Check if there are existing saved files.
     * Load the file if there is.
     * If not, will update user that the program will create a new list.
     * @param filePath
     */
    public TaskList load(String filePath){
        try{
            Path path = Paths.get(filePath);
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(path.toAbsolutePath().toString()));
            String outputFileLineText = "";
            while((outputFileLineText = bufferedReader.readLine()) != null){
                String command = outputFileLineText.substring(0, 3);
                switch(command){
                case "[T]":
                    addToDoTaskFromSavedList(taskList, outputFileLineText);
                    break;
                case "[D]":
                    addDeadlineTaskFromSavedList(taskList,outputFileLineText);
                    break;
                case "[E]":
                    addEventTaskFromSavedList(taskList,outputFileLineText);
                    break;
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e){
            System.out.println("There is no such file, a new file named duke.txt will be created");

        } catch(IOException e){
            System.out.println("There is no text in the file");
            System.out.println(e);
        }
        return taskList;
    }

    /**
     * Adds todo task from saved list to program list.
     * @param taskList
     * @param userInput
     */
    private static void addToDoTaskFromSavedList(TaskList taskList, String userInput) {
        boolean completed = false;
        String taskDescription = "";
        if(userInput.contains("[X]")){
            completed = true;
        } else{
            completed = false;
        }
        taskDescription = userInput.substring(userInput.indexOf(" ", 7));
        ToDo toDoTask = new ToDo(taskDescription
                ,completed);
        taskList.addTasks(toDoTask);
        toDoTask.initialiseToDo();
    }

    /**
     * Adds Event task from saved list to program list.
     * @param taskList
     * @param userInput
     */
    private static void addEventTaskFromSavedList(TaskList taskList, String userInput) {
        boolean completed = false;
        String taskDescriptionOnly = "";
        if(userInput.contains("[X]")){
            completed = true;
        } else{
            completed = false;
        }
        taskDescriptionOnly = userInput.substring(userInput.indexOf(" ", 7), userInput.indexOf("/"));
        Events eventTask = new Events( taskDescriptionOnly,
                completed, parser.identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(eventTask);
        eventTask.initialiseEvent();
    }

    /**
     * Adds deadline task from saved list to program list.
     * @param taskList
     * @param userInput
     */
    private static void addDeadlineTaskFromSavedList(TaskList taskList, String userInput) {
        boolean completed = false;
        String taskDescriptionOnly = "";
        if(userInput.contains("[X]")){
            completed = true;
            //taskDescriptionOnly = userInput.substring(userInput.indexOf(" ", 6), userInput.indexOf("/"));
        } else{
            completed = false;
            //taskDescriptionOnly = userInput.split(" ", 4)[3];
        }
        taskDescriptionOnly = userInput.substring(userInput.indexOf(" ", 7), userInput.indexOf("/"));
        Deadline deadLineTask = new Deadline(taskDescriptionOnly,
                completed, parser.identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(deadLineTask);
        deadLineTask.initialiseDeadline();
    }

    /**
     * Update the Duke text file.
     * @param taskList
     */
    public void updateOutputFile(TaskList taskList){
        try {
            String dir = System.getProperty("user.dir");
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(dir + "\\duke.txt"));
            for (int i = 0; i < taskList.savedTasks.size(); i++) {
                bufferedWriter.write(taskList.savedTasks.get(i).toString() + "\n");
            }
            bufferedWriter.close();
        } catch(IOException e){
            return;
        }
    }
}
