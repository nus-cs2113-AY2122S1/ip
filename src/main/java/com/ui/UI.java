package com.ui;

/**
 * Represents an ui interface of duke that help to auto indent the duke output to users.
 * outputLines to store the lines that duke want to output, LineCount is the number of lines
 */
public class UI {
    private static final String[] outputLines = new String[100];
    private static int LineCount = 0;
    private final static String LINESEPARATER = "\t______________________________________________________________________";

    /**
     * Display the welcome messages and Duke logo
     */
    public void welcome(){
        printLine();
        System.out.println("\tHello from");
        System.out.println("\t ____        _        ");
        System.out.println("\t| _ \\ _   _| | _____ ");
        System.out.println("\t| | | | | | | |/ / _ \\");
        System.out.println("\t| |_| | |_| |   <  __/");
        System.out.println("\t|____/ \\__,_|_|\\_\\___|");
        endLine();
    }

    /**
     * Prints the line separator
     */
    public void printLine() {
        System.out.println(LINESEPARATER);
    }

    /**
     * Store the line output into outputLines, waiting to output.
     *
     * @param line a single line that Duke wants to output
     */
    public void print(String line) {
        outputLines[LineCount++] = line;
    }

    /**
     * auto indent and output all the lines that duke want to output so that the entire output looks neat.
     * and print a line separator at the end.
     */
    public void endLine() {
        int max_len = 0;
        for (int i = 0; i < LineCount; i++) {
            if (outputLines[i].length() > max_len) {
                max_len = outputLines[i].length();
            }
        }
        for (int i = 0; i < LineCount; i++) {
            for (int j = 0; j < LINESEPARATER.length() - max_len + 3; j++) {
                System.out.print(" ");
            }
            System.out.println(outputLines[i]);
        }
        printLine();
        System.out.print("\t");
        LineCount = 0;
    }
}
