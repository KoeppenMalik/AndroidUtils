package de.malik.autils.managers;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.malik.autils.utils.AUFilePrinter;
import de.malik.autils.utils.AUFileReader;

public class FileManager {

    public static final String TAG = "FileManager";
    public static final String DEFAULT_COMMENT_PREFIX = "##";

    private static final Map<String, File> CREATED_FILES = new HashMap<>();
    private static final Map<String, File> CREATED_FOLDERS = new HashMap<>();

    public static String commentPrefix = DEFAULT_COMMENT_PREFIX;

    /**
     * creates a file with the specified name in the given folder
     * @param folder the folder where the file will be created
     * @param fileName the name of the file
     * @return the file, if it was successfully created, null otherwise
     * @throws IOException if an I/O error occurred or the folder doesn't exist
     */
    public static File createFiles(File folder, String fileName) throws IOException {
        boolean isFileCreated = false;
        if (!folder.exists()) {
            throw new IOException("Folder doesn't exist");
        }
        File file = new File(folder, fileName);
        isFileCreated = file.createNewFile();
        if (!isFileCreated) {
            return null;
        }
        CREATED_FILES.put(fileName, file);
        return file;
    }

    /**
     * creates a folder at the given path with the specified name
     * @param path the path where the folder will be created
     * @param folderName the name of the folder
     * @return the created folder
     */
    public static File createFolder(String path, String folderName) {
        boolean isFolderCreated = false;
        File folder = new File(path, folderName);
        if (!folder.exists()) {
            isFolderCreated = folder.mkdir();
        }
        if (!isFolderCreated) {
            Log.i(TAG, "Folder was not created, does it already exist?");
        }
        CREATED_FOLDERS.put(folderName, folder);
        return folder;
    }

    /**
     *
     * @param folderName the name of the required folder
     * @return a folder with the given name if there is one, null otherwise
     */
    public static File getFolder(String folderName) {
        return CREATED_FOLDERS.get(folderName);
    }

    /**
     * searches in the specified folder for the given file name
     * @param folder the folder where the files is located
     * @param fileName the name of the file
     * @return the file with the specified name if there is one, null otherwise
     */
    public static File getFile(File folder, String fileName) {
        File result = null;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    result = file;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * creates a new instance of AUFileReader class
     * @return a new instance of AUFileReader class. If commentPrefix is rather empty or null, null will be returned
     */
    public static AUFileReader getFileReader() {
        if (commentPrefix == null || commentPrefix.equals("")) {
            return null;
        }
        return new AUFileReader(commentPrefix);
    }

    /**
     * creates a new instance of AUFilePrinter class
     * @return a new instance of AUFilePrinter class
     */
    public static AUFilePrinter getFilePrinter() {
        return new AUFilePrinter();
    }
}
