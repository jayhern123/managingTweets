package com.jay.tweets;

import java.io.IOException;
import java.net.URISyntaxException;

import com.jay.tweets.factory.AuthTokensFactory;
import com.jay.tweets.factory.TwitterApiFactory;
import com.jay.tweets.service.FileWriter;
import com.jay.tweets.service.GetUserID;
import com.twitter.clientlib.api.TwitterApi;

/*
 * Sample code to demonstrate the use of the v2 Tweets endpoint
 * */
public class TweetsDemo {

	public static void main(String args[]) throws IOException, URISyntaxException {
		TwitterApi apiClient = TwitterApiFactory.getAuthenticatedTwitterApiClient(AuthTokensFactory.getTokensFromSecretFile());
		String user = GetUserID.getMyUserData(apiClient);
		FileWriter.writeToFile(user, "UserResponseData.txt");
  	}
}