package com.jay.tweets.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jay.tweets.model.EnvGetter;

/**
 * File writer
 */
public class FileWriter {
    private static final Logger LOGGER = Logger.getLogger(FileWriter.class.getName());
    private static final String RESPONSE_OUTPUT_ENV = "RESPONSE_OUTPUT";
    private static final String DEFAULT_PATH = "C:/TwiiterApiResponseData/";

    /**
     * Writes a list of string to the given file. If the file does not exist it
     * creates a new one for you
     * 
     * @param jsonStrings the list of strings to write to the file
     * @param fileName    the name of the file to append to (or create)
     * 
     * @apiNote this will overwrite the exisiting file
     */
    public static void writeToFile(List<String> jsonStrings, String fileName) {
        jsonStrings.forEach(js -> writeToFile(js, fileName));
    }

    /**
     * Writes a string to the given file. If the file does not exist it
     * creates a new one for you
     * 
     * @param jsonStrings the list of strings to write to the file
     * @param fileName    the name of the file to append to (or create)
     * 
     * @apiNote this will overwrite the exisiting file
     */
    public static void writeToFile(String jsonString, String fileName) {
        try (BufferedWriter  writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getOutputDirectory() + fileName), "utf-8"))) {
            if(jsonString != null && !jsonString.isEmpty()) {
                writer.write(jsonString);
                writer.newLine();
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "COULD NOT RELEASE RESOURCES! : {0}", e.getLocalizedMessage());
        }
    }

    /**
     * Deletes the response output file
     * 
     * @param fileName  the file to delete
     * 
     * @return True; if successfully deleted
     */
    protected static boolean deleteResponseData(String fileName) {
        boolean flag = false;
        try {
            flag = Files.deleteIfExists(Paths.get(getOutputDirectory() + fileName));

        } catch (NoSuchFileException e) {
            LOGGER.log(Level.SEVERE, "COULD NOT DELETE FILE. FILE '%1$2' DOES NOT EXSIT! {0}", fileName);


        } catch (DirectoryNotEmptyException e) {
            LOGGER.log(Level.SEVERE, "COULD NOT DELETE FILE. INPUT WAS A NON_EMPTY DIRECTORY INSTEAD! {0}", e.getLocalizedMessage());

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "COULD NOT DELETE THE FILE. INVALID PERMISSIONS {0}", e.getLocalizedMessage());
        }

        return flag;
    }

    /**
     * Gets the output directory from the evrionment vars. If the output directory
     * does not exist this will create a default one in C:/TwiiterApiResponseData
     * 
     * @return the output directory file path
     */
    protected static String getOutputDirectory() {
        String outputDir;

        String rootDirPath = EnvGetter.getEnv(RESPONSE_OUTPUT_ENV);
        if(rootDirPath != null) {
            outputDir = Paths.get(rootDirPath).toAbsolutePath() + "/";
        
        } else if(Paths.get(DEFAULT_PATH) == null) {
            new File(DEFAULT_PATH).mkdirs();
            outputDir = DEFAULT_PATH;
        
        } else {
            outputDir = DEFAULT_PATH;
        }

        return outputDir;
    }
}
