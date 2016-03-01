package net.apporbit.lab.bot.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Async;
import com.amazonaws.services.ec2.AmazonEC2AsyncClient;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementAsyncClient;

public class AWSAuthService {


    /** AWS accessKey. */
    private String accessKey;
    /** AWS secretKey.*/
    private String secretKey;
    /** AWS secretKey.*/
    private String region;

    /** The thread sleep time after. */
    private static final int AWS_HTTP_CONNECTION_TIME_OUT_TIME = 900000;


	/**
     * Constructor to set cloud config and configService.
     * @param cloudConfig to set
     * @param configService object reference to set
     */
    public AWSAuthService(String accessKey, String secretKey, String region) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.region = region;
    }

    /**
     * Get the AWS ec2 client.
     *
     * @return OpensStack client
     * @throws ApplicationException if any error
     */
    public AmazonEC2Async getAmazonEC2Client() throws Exception {

        AmazonEC2Async ec2 = null;
        try {

            BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
            ClientConfiguration clientConfiguration = new ClientConfiguration();
            clientConfiguration.setConnectionTimeout(AWS_HTTP_CONNECTION_TIME_OUT_TIME);

            ec2 = new AmazonEC2AsyncClient(awsCreds);

            if (region != null) {
                ec2.setRegion(Region.getRegion(Regions.fromName(region)));
            }

        } catch (Exception e) {
            System.err.println("AWS EC2 Authentication failed due to " + e.getMessage());
            e.printStackTrace(System.err);
            throw new Exception("Authentication credentials were missing or incorrect");
        }

        return ec2;
    }

    /**
     * Get the AWS IAM client.
     *
     * @return the IAM CLient.
     * @throws ApplicationException if any error
     */
    public AmazonIdentityManagementAsyncClient getAmazonIAMClient() throws Exception {

        try {
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

            return new AmazonIdentityManagementAsyncClient(awsCreds);

        } catch (Exception e) {
        	System.err.println("AWS Identity Authentication failed due to " + e.getMessage());
        	e.printStackTrace(System.err);
            throw new Exception("Authentication credentials were missing or incorrect");
        }
    }

	/**
	 * @return the accessKey
	 */
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * @param accessKey the accessKey to set
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
}
