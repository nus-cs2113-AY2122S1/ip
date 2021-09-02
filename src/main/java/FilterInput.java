public class FilterInput {


    /**
     * Returns lateral location of the specified position.
     *
     * @param words String input by user.
     */
    public static void checkCommand(String[] words) {
        String[] descriptionInput = descriptionInput(words);
        switch (words[0]) {
        case "bye":
            Greet.printGoodbyeMessage();
            break;
        case "list":
            Greet.printList();
            break;
        case "done":
            int taskNumber = Integer.parseInt(words[1]);
            //might need to catch errors in the future
            Greet.checkDoneTask(taskNumber);
            break;
        case "todo":
            Todo todo = new Todo(descriptionInput[0]);
            Greet.addTask(todo);
            break;
        case "deadline":
            Deadlines deadline = new Deadlines(descriptionInput[0], descriptionInput[1]);
            Greet.addTask(deadline);
            break;
        case "event":
            Event event = new Event(descriptionInput[0], descriptionInput[1]);
            Greet.addTask(event);
            break;
        default:
            System.out.println("invalid command");
//            command = command + in.nextLine();
//            Greet.addTask(command);
        }
    }

    private static String[] descriptionInput(String[] words) {
        String[] output = new String[2];
        output[0] = "";
        output[1] = "";

        int changeString = 0;
        // mistake over here
        for (int counter = 1; counter < words.length; counter++) {
            if (words[counter].equals("/by") || words[counter].equals("/at")) {
                //Switch from string0 to string1
                changeString = 1;
                continue;
            }
            output[changeString] += " ";
            output[changeString] += words[counter];

        }
        return output;
    }

    private static boolean isDescription(int counter, String[] words) {
        return counter < words.length;
    }

    private static boolean isDate(int counter, String[] words) {
        return counter < words.length;
    }


}
