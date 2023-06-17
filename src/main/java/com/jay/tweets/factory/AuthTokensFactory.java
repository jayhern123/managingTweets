package com.jay.tweets.factory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.jay.tweets.model.AuthTokens;
import com.jay.tweets.model.EnvGetter;

/**
 * Reads tokens from a secret file to be used for authenticated requests
 */
public class AuthTokensFactory {
    private static final Logger LOGGER = Logger.getLogger(AuthTokensFactory.class.getName());

    private static final String FILE_VAR = "TOKEN_FILE";
    private static final String DELIMITER = "=";

    private static final String API_KEY = "API_KEY";
    private static final String API_KEY_SECRET = "API_KEY_SECRET";
    private static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN";
    private static final String ACCESS_TOKEN_SECRET = "ACCESS_TOKEN_SECRET";
    private static final String BEARER_TOKEN_KEY = "BEARER_TOKEN";

    private AuthTokensFactory() {
        // no-args
    }

    /**
     * Gets the token values from the given resource file
     * 
     * @param tokenFile  the resource file to read the values from
     * 
     * @return the auth token object
     */
    public static AuthTokens getTokensFromSecretFile() {
        File tokensFile = getTokensFile();
        if( tokensFile == null ) {
            LOGGER.log(Level.WARNING, "COULD NOT FIND TOKENS FILE! USING DEFAULT VALUES");
            return new AuthTokens();
        }

        Map<String, String> tokenMap = getFileProperties(tokensFile);
        if( tokenMap.isEmpty() ) {
            LOGGER.log(Level.WARNING, "FILE PROCESSING ERROR! USING DEFAULT VALUES");
            return new AuthTokens();
        }

        return new AuthTokens().withApiKey(tokenMap.get(API_KEY))
                                .withApiKeySecret(tokenMap.get(API_KEY_SECRET))
                                .withAccessTokenKey(tokenMap.get(ACCESS_TOKEN_KEY))
                                .withAccessTokenSecret(tokenMap.get(ACCESS_TOKEN_SECRET))
                                .withBearerKey(tokenMap.get(BEARER_TOKEN_KEY));
    }

    /**
     * Gets the file that contains the secret tokens
     * 
     * @return the file
     */
    private static File getTokensFile() {
        File file = null;
        
        String filePath = EnvGetter.getEnv(FILE_VAR);
        if(filePath != null) {
            file = Paths.get(filePath).toFile();
        }

        return file;
    }

    /**
     * Reads in the file and outputs a map of all of the token key value pairs
     * 
     * @param tokensFile  the tokens file
     * 
     * @return a map with key = token key, and value = token value
     */
    private static Map<String, String> getFileProperties(File tokensFile) {
        Map<String, String> map = new HashMap<>();
        try(Stream<String> lines = Files.lines(tokensFile.toPath())){
            lines.filter(line -> line.contains(DELIMITER)).forEach(
                line -> map.putIfAbsent(line.split(DELIMITER)[0], line.split(DELIMITER)[1])
            );
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "COULD NOT READ TOKEN FILE! {0}", e.getLocalizedMessage());
        }

        return map;
    }
}
