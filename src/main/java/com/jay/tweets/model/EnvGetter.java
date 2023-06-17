package com.jay.tweets.model;

/**
 * Class used to inject system environement varaibles
 */
public class EnvGetter {
    
    private EnvGetter() {
        // no-args
    }

    /**
     * Gets the system environment varaible value
     * 
     * @param varName the var name key
     * 
     * @return the string value for the var
     */
    public static String getEnv(String varName) {
        return System.getenv(varName);
    }
}
