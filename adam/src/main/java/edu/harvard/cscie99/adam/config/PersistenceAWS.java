package edu.harvard.cscie99.adam.config;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

public class PersistenceAWS 
{

	public AWSCredentials getAWSCreds()
	{
	    /*
	     * The ProfileCredentialsProvider will return your [AdamJavaSDK]
	     * credential profile by reading from the credentials file located at
	     * (C:\\Users\\Adam\\.aws\\credentials).
	     * ~/.aws/credentials on Linux, OS X, or Unix
	     */
	    AWSCredentials credentials = null;
	    try
	    {
	        credentials = new ProfileCredentialsProvider("AdamJavaSDK").getCredentials();
	    }
	    catch (Exception e)
	    {
	        throw new AmazonClientException(
	                "Cannot load the credentials from the credential profiles file. " +
	                "Please make sure that your credentials file is at the correct " +
	                "location (C:\\Users\\apgalush\\.aws\\credentials), and is in valid format.",
	                e);
	    }
		return credentials;
	}
	
}
