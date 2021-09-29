package com;
import com.duke.Duke;
import com.task.*;

import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        Duke duke = new Duke();
        //duke.greet();
        do {
            line = in.nextLine();
            line = line.toLowerCase();
            switch(line) {
            case "bye":
            case "end":
                duke.endDuke();
                break;
            case "hi":
            case "hello":
                duke.greet();
                break;
            case "list":
                duke.listOut();
                break;
            default:
                if(line.contains("done")) {
                    duke.markDone(line);
                    break;
                }
                if(line.contains("delete")) {
                    duke.deleteItem(line);
                    break;
                }
                if(line.contains("todo")) {
                    line = line.substring(5);
                    duke.addList(new Todo(line));
                    break;
                }
                if(line.contains("deadline")) {

                    String SEPARATOR="";
                    if(line.contains("by")) {
                        SEPARATOR = "by";
                    }
                    else if(line.contains("@")) {
                        SEPARATOR = "@";
                    }
                    else if(line.contains("/")) {
                        SEPARATOR = "/";
                    }
                    else{
                        duke.unknownAction();
                        break;
                    }
                    String time = line.substring(line.indexOf(SEPARATOR)+1);
                    line = line.substring(9,line.indexOf(SEPARATOR));
                    duke.addList(new Deadline(line,time));
                    break;
                }
                if(line.contains("event")) {
                    int SEPARATOR_START_INDEX=0;
                    int SEPARATOR_END_INDEX=0;
                    int DESCRIPTION_INDEX=6;
                    if(line.contains("by")) {
                        SEPARATOR_START_INDEX = line.indexOf("by");
                        SEPARATOR_END_INDEX= SEPARATOR_START_INDEX+1;
                    }
                    else if(line.contains("at")) {
                        SEPARATOR_START_INDEX = line.indexOf("at");
                        SEPARATOR_END_INDEX= SEPARATOR_START_INDEX+1;
                    }
                    else if(line.contains("@")) {
                        SEPARATOR_START_INDEX = line.indexOf("@");
                        SEPARATOR_END_INDEX= SEPARATOR_START_INDEX;
                    }
                    else if(line.contains("/")) {
                        SEPARATOR_START_INDEX = line.indexOf("/");
                        SEPARATOR_END_INDEX= SEPARATOR_START_INDEX;
                    }
                    else{
                        duke.unknownAction();
                        break;
                    }
                    String time = line.substring(SEPARATOR_END_INDEX+1);
                    line = line.substring(DESCRIPTION_INDEX,SEPARATOR_START_INDEX);
                    duke.addList(new Event(line,time));
                    break;
                }
                else {
                    duke.unknownAction();
                }
            }
        }while(duke.getStatus());
    }
}
