package com.ui;

public class UI {
    private String[] outputLines = new String[100];
    private int LineCount = 0;
    private final static String LINESEPARATER = "\t______________________________________________________________________";

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

    public void printLine() {
        System.out.println(LINESEPARATER);
    }

    public void print(String line) {
        outputLines[LineCount++] = line;
    }

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
