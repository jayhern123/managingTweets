package com.jay.tweets.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.jay.tweets.model.AuthTokens;
import com.jay.tweets.model.EnvGetter;

/**
 * Test
 */
class AuthTokensFactoryTest {

    @Test
    void testReadingExampleTokenFile() throws URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("test.env");
        String testFilePath = new File(resource.toURI()).getAbsolutePath();
        
        try(MockedStatic<EnvGetter> envMock = mockStatic(EnvGetter.class)) {
            envMock.when(() -> EnvGetter.getEnv(anyString())).thenReturn(testFilePath);

            AuthTokens results = AuthTokensFactory.getTokensFromSecretFile();

            assertEquals("TOKEN1", results.getApiKey());
            assertEquals("TOKEN2", results.getApiKeySecret());
            assertEquals("TOKEN3", results.getAccessTokenKey());
            assertEquals("TOKEN4", results.getAccessTokenSecret());
            assertEquals("TOKEN5", results.getBearerKey());
        }
    }

    @Test
    void testReadingExampleTokenFile_CantFindFile_ShouldReturnDefaultValues() {

        try(MockedStatic<EnvGetter> envMock = mockStatic(EnvGetter.class)) {
            envMock.when(() -> EnvGetter.getEnv(anyString())).thenReturn(null);

            AuthTokens results = AuthTokensFactory.getTokensFromSecretFile();

            assertEquals("", results.getApiKey());
            assertEquals("", results.getApiKeySecret());
            assertEquals("", results.getAccessTokenKey());
            assertEquals("", results.getAccessTokenSecret());
            assertEquals("", results.getBearerKey());
        }
    }
}
