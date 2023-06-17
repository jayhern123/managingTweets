package com.jay.tweets.factory;

import com.jay.tweets.model.AuthTokens;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.TwitterCredentialsOAuth1;
import com.twitter.clientlib.api.TwitterApi;

/**
 * Factory class for the twitter api client
 */
public class TwitterApiFactory {

    private TwitterApiFactory() {
        // no-args
    }

    /**
     * Creates a new instance of the twitter api client w/ credentials
     * 
     * @param authTokens the authentication tokens object
     * 
     * @return the twitter api object
     */
    public static TwitterApi getAuthenticatedTwitterApiClient(AuthTokens authTokens) {
        TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(authTokens.getBearerKey());
        TwitterCredentialsOAuth1 oauth1Creds = new TwitterCredentialsOAuth1(authTokens.getApiKey(), 
                                                                            authTokens.getApiKeySecret(), 
                                                                            authTokens.getAccessTokenKey(), 
                                                                            authTokens.getAccessTokenSecret());

        TwitterApi apiInstance = new TwitterApi();
        apiInstance.setTwitterCredentials(credentials);
        apiInstance.setTwitterCredentials(oauth1Creds);
        return apiInstance;
    }




}
