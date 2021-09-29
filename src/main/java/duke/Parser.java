package duke;

public class Parser {

    public static boolean processInput(TaskList tasks, String input) {
        boolean isBye = false;
        if (input.equals("bye")){ //check if bye
            isBye = true;
        } else if (input.equals("list")) { //check if list
            processList(tasks);
        } else if (input.contains("done") ) { //check if done
            processDone(tasks, input);
        } else if (input.contains("delete") ) { //check if delete
            processDelete(tasks, input);
        } else {
            try {
                tasks.counter = processTasks(tasks, input); //process tasks
            } catch ( IllegalTaskException e ) {
                System.out.println("Please include /by for deadline and /at for event");
            }
        }
        return isBye;
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
        if ( ( input.contains("deadline") || input.contains("event") ) && !input.contains("/")){
            throw new IllegalTaskException();
        }
        if (input.contains("todo")) {
            String description = input.substring(5);
            ToDo newTask = new ToDo(description);
            tasks.list.add(tasks.counter, newTask);
            tasks.counter += 1;
            Ui.printAddedTaskMessage(newTask, tasks.counter);
        } else if (input.contains("deadline")) {
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
        } else if (input.contains("event")) {
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
