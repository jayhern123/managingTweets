package com.jay.tweets.factory;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.jay.tweets.model.AuthTokens;
import com.twitter.clientlib.api.TwitterApi;

/**
 * Tests
 */
class TwitterApiFactoryTest {
    
    /**
     * Just a simple test
     */
    @Test
    void testGetAuthenticatedTwitterApiClient() {
        AuthTokens tokens = new AuthTokens();

        TwitterApi client = TwitterApiFactory.getAuthenticatedTwitterApiClient(tokens);

        assertFalse(client.getApiClient().getAuthentications().isEmpty());
    }
}
