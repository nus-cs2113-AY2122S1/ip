import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        } /*catch(StringIndexOutOfBoundsException e){
            System.out.println("Empty file. We will rewrite the file\n");
        }*/
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
        ToDo toDoTask = new ToDo(parser.identifyStorageUserToDoTask(userInput)
                ,completed);
        taskList.addTasks(toDoTask);
        System.out.println(toDoTask.toString());
    }

    /**
     * Adds Event task from saved list to program list.
     * @param taskList
     * @param userInput
     */
    private static void addEventTaskFromSavedList(TaskList taskList, String userInput) {
        boolean completed = false;
        if(userInput.contains("[X]")){
            completed = true;
        } else{
            completed = false;
        }
        Events eventTask = new Events(parser.identifyStorageUserTask(userInput),
                completed, "/" + parser.identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(eventTask);
        System.out.println(eventTask.toString());
    }

    /**
     * Adds deadline task from saved list to program list.
     * @param taskList
     * @param userInput
     */
    private static void addDeadlineTaskFromSavedList(TaskList taskList, String userInput) {
        boolean completed = false;
        if(userInput.contains("[X]")){
            completed = true;
        } else{
            completed = false;
        }
        Deadline deadLineTask = new Deadline(parser.identifyStorageUserTask(userInput),
                completed, "/" + parser.identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(deadLineTask);
        System.out.println(deadLineTask.toString());
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
