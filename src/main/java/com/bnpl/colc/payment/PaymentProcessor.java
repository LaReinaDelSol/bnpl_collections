package com.bnpl.colc.payment;

import java.nio.charset.StandardCharsets;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
import com.bnpl.colc.dto.BNPLAccountData;
import com.bnpl.colc.dto.Instalment;

public class PaymentProcessor {

    public void processPayment(String region, String functionName, String payload) {
        
    	// Instantiate credentials
        final String AWS_ACCESS_KEY_ID = "AKIA5PTIVJ2P4DXQQU2E";
        final String AWS_SECRET_ACCESS_KEY = "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";

        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);
       
        // Create an InvokeRequest with AWS function name and payload
        String payLoad = null;
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName("arn:aws:lambda:us-east-1:926858694303:function:mau_lambda")
                .withPayload(payLoad);
/*                .withPayload("{\n" +
                        " \"Hello \": \"Paris\",\n" +
                        " \"countryCode\": \"FR\"\n" +
                        "}");*/
        		
        // Instantiate InvokeResult to capture function invocation response
        InvokeResult invokeResult = null;

        try {
        	// Instantiate AWSLambdaClientBuilder to build the Lambda client with predefined credentials and regions to invoke the Lambda function 
            AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.US_EAST_1).build();
            
            // Invoke the function and capture response
            invokeResult = awsLambda.invoke(invokeRequest);

            // Stringify payload
            String ans = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);

            //write out the return value
            System.out.println(ans);

        } catch (ServiceException se) {
            System.out.println(se);
        }

        System.out.println(invokeResult.getStatusCode());
    }
    
    public double processPayment(BNPLAccountData bnplAccountData, Instalment instalmentToBeCollected) {
    	// calculate total amount to be charged
    	double amountToBeCollected =+ instalmentToBeCollected.getAmount();   	
    	if(bnplAccountData.getBnplPaymentData().isDefaultFeeApplied()) {
    		amountToBeCollected =+ instalmentToBeCollected.getFeeAmount();
    	}
    	return amountToBeCollected;
    	//TODO: trigger Revio with amountToBeCollected
    }
}