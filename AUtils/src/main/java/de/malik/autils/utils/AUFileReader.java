package de.malik.autils.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AUFileReader {

    /**
     * a string which is used to check if a line in a file is commented out
     */
    private String mCommentPrefix;

    /**
     * creates a new instance of AUFileReader class
     * @param commentPrefix the comment prefix which will be used to check if a line in the file is commented out
     */
    public AUFileReader(String commentPrefix) {
        mCommentPrefix = commentPrefix;
    }

    /**
     * reads the lines of the given file and puts it into an ArrayList. every element is equivalent to a whole line
     * @param file the file which will be read
     * @return an ArrayList containing all the line data of the file
     * @throws IOException is an I/O error occurred
     */
    public ArrayList<String> readLines(File file) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = createReader(file);
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                ArrayList<Character> commentChars = new ArrayList<>();
                for (int i = 0; i < mCommentPrefix.toCharArray().length; i++) {
                    commentChars.add(line.charAt(i));
                }
                if (commentChars.contains(commentChars.containsAll(Arrays.asList(mCommentPrefix.toCharArray())))) {
                    lines.add(line);
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return lines;
    }

    /**
     * creates a new instance of BufferedWriter class
     * @param file the file which will be read
     * @return a new instance of BufferedWriter class
     * @throws IOException if an I/O error occurred
     */
    public BufferedReader createReader(File file) throws IOException {
        return new BufferedReader(new FileReader(file));
    }
}
