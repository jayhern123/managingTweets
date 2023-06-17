package com.jay.tweets.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.twitter.clientlib.model.SingleUserLookupResponse;
import com.twitter.clientlib.model.User;

/**
 * Test class
 */
class FileWriterTest {

    @Test
    void testFileHandling() {
        // given 
        String fileName = "example.txt";
        SingleUserLookupResponse userData = getTestUserResponse();
        assertNotNull(userData);

        // test write
        FileWriter.writeToFile(userData.toString(), fileName);
        Path newFilePath = Paths.get(FileWriter.getOutputDirectory() + fileName);
        assertTrue(Files.exists(newFilePath));

        // test delete
        FileWriter.deleteResponseData(fileName);
        assertFalse(Files.exists(newFilePath));
    }

    /**
     * Creates a test user response object
     * 
     * @return  the single user lookup response
     */
    private static SingleUserLookupResponse getTestUserResponse() {
        User user = new User();
        user.setId("6522284856");
        user.setName("twitter dev");
        user.setUsername("twitter dev");

        SingleUserLookupResponse userResponse = new SingleUserLookupResponse();
        userResponse.setData(user);

        return userResponse;
    }
}
