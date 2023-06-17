package com.jay.tweets.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.SingleUserLookupResponse;

/**
 * Sends a request that gets user data
 */
public class GetUserID {
    private static final Logger LOGGER = Logger.getLogger(GetUserID.class.getName());
    private static final String ERR = "THERE WAS AN ERROR GETTING THE USER INFO! STATUS CODE : %1$s RESPONSE BODY : %2$s REASON : %3$s";

    /**
     * Requests my user info 
     * 
     * @param twitterClient  the twitter api instance
     * 
     * @return the string value for user info
     */
    public static String getMyUserData(TwitterApi twitterClient) {
        
        String user;
        try {
            SingleUserLookupResponse  apiResponse = twitterClient.users().findMyUser(null, null, null);
            String data = apiResponse.getData().toJson();
            LOGGER.log(Level.INFO, data);
            user = data;

        } catch (ApiException e) {
            LOGGER.log(Level.SEVERE, () -> String.format(ERR, e.getCode(), e.getResponseBody(), e.getLocalizedMessage()));
            user = "";
        }

        return user;
    }

}
