package xRoss.storage;

/**
 * Interface containing methods to save to and read from a specified text file.
 */
public interface FileManager {

    /**
     * Read content from a specified text file.
     */
    void readFromFile();

    /**
     * Write content to specified text file to store them.
     */
    void saveToFile();

}
