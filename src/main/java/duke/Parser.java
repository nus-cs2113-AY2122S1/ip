package duke;

public class Parser {
    /**
     * Returns a boolean to break the while loop.
     * This method also processes what user inputs.
     * @param tasks is an instance of the public class TaskList,
     *              where the list of tasks and counter for number of tasks
     *              can be found and is updated based on user inputs.
     * @param input is the entire chunk of message that user inputs.
     * @return boolean whether input starts with 'Bye'
     */
    public static boolean processInput(TaskList tasks, String input) {
        boolean isBye = false;
        if (input.equals("bye")){ //check if bye
            isBye = true;
        } else if (input.startsWith("list")) { //check if list
            processList(tasks);
        } else if (input.startsWith("done") ) { //check if done
            processDone(tasks, input);
        } else if (input.startsWith("delete") ) { //check if delete
            processDelete(tasks, input);
        } else if (input.startsWith("find") ) {
            processFind(tasks, input);
        } else {
            try {
                tasks.counter = processTasks(tasks, input); //process tasks
            } catch ( IllegalTaskException e ) {
                System.out.println("Please include /by for deadline and /at for event");
            }
        }
        return isBye;
    }

    private static void processFind(TaskList tasks, String input) {
        int findCount = 0;
        String keyword = input.substring(5);
        Ui.printFindMessageStart();
        for(int i = 0; i < tasks.counter; i += 1) {
            Task currTask = tasks.list.get(i);
            if( currTask.getDescription().contains(keyword) ){
                Ui.printListOfTaskSubMessage(currTask,i);
                findCount += 1;
            }
        }
        if(findCount == 0) {
            Ui.printFindNothingMessage();
        }
        Ui.printFindMessageEnd();
    }

    private static void processDelete(TaskList tasks, String input) {
        int deletePos = input.indexOf("delete");
        if (tasks.list.size() != 0) {
            if (input.length() < deletePos + 7) {
                Ui.printDeleteButNotSpecificMessage(); //when input contains delete but no number
            } else {
                processValidDelete(tasks, input, deletePos); //when input contains delete and specified number
            }
        } else {
            Ui.printDeleteButEmptyMessage(); //when input contains delete but list is empty
        }
    }

    private static void processValidDelete(TaskList tasks, String input, int deletePos) {
        String itemNumDone = input.substring(deletePos + 7, deletePos + 8);
        int itemNum = Integer.parseInt(itemNumDone);
        Ui.printDeleteMessage(tasks.list, itemNum);
        tasks.list.remove(itemNum - 1);
        tasks.counter -= 1;
    }

    private static void processList(TaskList tasks) {
        if (tasks.list.size() != 0){
            Ui.printListMessage(tasks);
        } else {
            Ui.printListButEmptyMessage();
        }
    }

    private static void processDone(TaskList tasks, String input) {
        int donePos = input.indexOf("done");
        if (tasks.list.size() != 0) {
            if (input.length() < donePos + 5) {
                Ui.printDoneButNotSpecificMessage(); //when input contains done but no number
            } else {
                processValidDone(tasks, input, donePos); //when input contains done and specified number
            }
        } else {
            Ui.printDoneButEmptyMessage(); //when input contains done but list is empty
        }
    }

    private static void processValidDone(TaskList tasks, String input, int donePos) {
        String itemNumDone = input.substring(donePos + 5, donePos + 6);
        int itemNum = Integer.parseInt(itemNumDone);
        tasks.list.get(itemNum - 1).setDone();
        Ui.printDoneMessage(tasks.list, itemNum);
    }

    private static int processTasks(TaskList tasks, String input) throws IllegalTaskException{
        if ( ( input.startsWith("deadline") || input.startsWith("event") ) && !input.contains("/")){
            throw new IllegalTaskException();
        }
        if (input.startsWith("todo")) {
            String description = input.substring(5);
            ToDo newTask = new ToDo(description);
            tasks.list.add(tasks.counter, newTask);
            tasks.counter += 1;
            Ui.printAddedTaskMessage(newTask, tasks.counter);
        } else if (input.startsWith("deadline")) {
            int donePos = input.indexOf("/"); //finds pos of '/'
            String description = input.substring(9,donePos);
            if (!input.substring(donePos + 1, donePos + 3).equals("by")) {
                throw new IllegalTaskException();
            }
            String date = input.substring(donePos + 4);
            Deadline newTask = new Deadline(description,date);
            tasks.list.add(tasks.counter, newTask);
            tasks.counter += 1;
            Ui.printAddedTaskMessage(newTask, tasks.counter);
        } else if (input.startsWith("event")) {
            int donePos = input.indexOf("/"); //finds pos of '/'
            String description = input.substring(6,donePos);
            if (!input.substring(donePos + 1, donePos + 3).equals("at")) {
                throw new IllegalTaskException();
            }
            String date = input.substring(donePos + 4);
            Event newTask = new Event(description,date);
            tasks.list.add(tasks.counter, newTask);
            tasks.counter += 1;
            Ui.printAddedTaskMessage(newTask, tasks.counter);
        } else {
            System.out.println("Please specify tasks: todo, deadline or event");
            System.out.println("Example - type in the following: todo read book");
        }
        return tasks.counter;
    }

}
