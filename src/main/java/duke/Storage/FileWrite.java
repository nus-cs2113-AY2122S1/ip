package duke.Storage;

import duke.ErrorHandling.ErrorStaticString;

import java.io.IOException;

public class FileWrite extends Storage{

    public void writeToFile(String s, boolean isAppend){
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(file, isAppend);
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(ErrorStaticString.ERROR_FILE_MESSAGE_WRITING);
        }
    }
}
