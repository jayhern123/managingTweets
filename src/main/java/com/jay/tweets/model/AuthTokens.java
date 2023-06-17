package com.jay.tweets.model;

/**
 * POJO for passing around auth tokens
 */
public class AuthTokens {

    private String apiKey;
    private String apiKeySecret;
    private String accessTokenKey;
    private String accessTokenSecret;
    private String bearerKey;

    /**
     * Default values constructor
     */
    public AuthTokens() {
        this("", "", "", "", "");
    }

    /**
     * Public constructor 
     * 
     * @param apiKey            a string value
     * @param apiKeySecret      a string value
     * @param accessTokenKey    a string value
     * @param accessTokenSecret a string value
     * @param bearerKey         a string value
     */
    public AuthTokens(String apiKey, String apiKeySecret, String accessTokenKey, String accessTokenSecret, String bearerKey) {
        this.apiKey = apiKey;
        this.apiKeySecret = apiKeySecret;
        this.accessTokenKey = accessTokenKey;
        this.accessTokenSecret = accessTokenSecret;
        this.bearerKey = bearerKey;
    }

    /**
     * Builder setter
     * 
     * @param apiKey the string value
     * 
     * @return the auth token object
     */
    public AuthTokens withApiKey(String apiKey) {
        this.apiKey = apiKey != null ? apiKey : "";
        return this;
    }

    /**
     * @return the api key
     */
    public String getApiKey() {
        return apiKey;
    }
    
    /**
     * Builder setter
     * 
     * @param apiKeySecret the string value
     * 
     * @return the auth token object
     */
    public AuthTokens withApiKeySecret(String apiKeySecret) {
        this.apiKeySecret = apiKeySecret != null ? apiKeySecret : "";
        return this;
    }

    /**
     * @return the api key secret
     */
    public String getApiKeySecret() {
        return apiKeySecret;
    }
    
    /**
     * Builder setter
     * 
     * @param accessTokenKey the string value
     * 
     * @return the auth token object
     */
    public AuthTokens withAccessTokenKey(String accessTokenKey) {
        this.accessTokenKey = accessTokenKey != null ? accessTokenKey : "";
        return this;
    }

    /**
     * @return the access token key
     */
    public String getAccessTokenKey() {
        return accessTokenKey;
    }
    
    /**
     * Builder setter
     * 
     * @param accessTokenSecret the string value
     * 
     * @return the auth token object
     */
    public AuthTokens withAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret != null ? accessTokenSecret : "";
        return this;
    }

    /**
     * @return the access token secret
     */
    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }
    
    /**
     * Builder setter
     * 
     * @param bearerKey the string value
     * 
     * @return the auth token object
     */
    public AuthTokens withBearerKey(String bearerKey) {
        this.bearerKey = bearerKey != null ? bearerKey : "";
        return this;
    }

    /**
     * @return the bearer key
     */
    public String getBearerKey() {
        return bearerKey;
    }
}   
