//package duke;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import static java.lang.System.lineSeparator;
//
//public class ManipulateFile {
//    public static void main(String[] args) throws IOException {
//        String filePath = "data/duke.txt";
//        String textToAdd = "T 1 read book\n" +
//                "D 0 return book June 6th\n" +
//                "E 0 project meeting Aug 6th 2-4pm\n" +
//                "T 1 join sports club";
//        File f = new File("data/duke.txt");
//        if (!f.exists()){
//            f.createNewFile();
//        }
//        if (!f.exists()){
//            f.mkdirs();
//        }
////        f.createNewFile();
////        f.mkdirs();
//
//        FileWriter fw = new FileWriter(filePath);
//        fw.write(textToAdd + lineSeparator());
//        fw.write(textToAdd);
//        for(Task task: Greet.getList()) {
////            T 1 read book
////            D 0 return book | June 6th
////            E 0 project meeting | Aug 6th 2-4pm
////            T 1 join sports club
//
//            fw.write(task.getTaskType() + " " + booleanInt(task.isDone) +
//                    " " + task.getDescription());
//            if (task instanceof Event) {
//                Event event = (Event)task;
//                fw.write(" " + event.getEventDate());
//            } else if (task instanceof Deadline){
//                Deadline deadline = (Deadline)task;
//                fw.write(" " + deadline.getDueDate());
//            }
//        }
//        fw.close();
//        Scanner s = new Scanner(f);
//        while (s.hasNext()) {
//            //System.out.println(s.nextLine());
//            parseInformation(s.nextLine());
//        }
//        System.out.println("full path: " + f.getAbsolutePath());
//        System.out.println("file exists?: " + f.exists());
//        System.out.println("is Directory?: " + f.isDirectory());
//        Greet.printList();
//    }
//
//    private static int booleanInt(boolean isDone) {
//        return isDone ? 1 : 0;
//    }
//
//    private static void parseInformation(String information) {
//        System.out.println(information);
//        String[] result = information.split(" ");
//        for(String r : result){
//            System.out.println(r);
//        }
//        checkTaskType(result);
//    }
//
//    private static void checkTaskType(String[] result) {
//        Task newTask;
//        switch(result[0]){
//        case "T":
//            newTask = new Todo(result[2], strToBoolean(result[1]));
//            Greet.addTask(newTask);
//            break;
//        case "D":
//            newTask = new Deadline(result[2],strToBoolean(result[1]),result[3]);
//            Greet.addTask(newTask);
//            break;
//        case "E":
//            newTask = new Event(result[2],strToBoolean(result[1]),result[3]);
//            Greet.addTask(newTask);
//            break;
//        }
//
//    }
//
//    private static boolean strToBoolean(String s) {
//        System.out.println(s);
//        if (s.equals('0')){
//
//            return false;
//        }
//        return true;
//    }
//
//}
