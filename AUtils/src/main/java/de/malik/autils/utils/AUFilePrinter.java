package de.malik.autils.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AUFilePrinter {

    /**
     * prints the given records into the given file
     * @param file the file where the records will be printed in
     * @param append if true, the current file data will be conserved, otherwise it will be overridden
     * @param records the records that will be printed into the file
     * @throws IOException if an I/O error occurred
     */
    public void printFile(File file, boolean append, String... records) throws IOException {
        PrintWriter printer = createPrinter(file, append);
        try {
            for (String record : records) {
                printer.println(record);
            }
        }
        finally {
            printer.flush();
            printer.close();
        }
    }

    /**
     * prints the given record into the given file
     * @param file the file where the record will be printed in
     * @param append if true, the current file data will be conserved, otherwise it will be overridden
     * @param record the record that will be printed into the file
     * @throws IOException if an I/O error occurred
     */
    public void printFile(File file, boolean append, String record) throws IOException {
        PrintWriter printer = createPrinter(file, append);
        try {
            printer.println(record);
        }
        finally {
            printer.flush();
            printer.close();
        }
    }

    /**
     * creates a new instance of PrintWriter class
     * @param file the file where to printer will write in
     * @param append if true, the current file data will be conserved, otherwise it will be overridden
     * @return a new instance of PrintWriter class
     * @throws IOException if an I/O error occurred
     */
    private PrintWriter createPrinter(File file, boolean append) throws IOException {
        return new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
    }
}
