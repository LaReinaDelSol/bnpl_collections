package com.bnpl.colc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SampleLambda implements RequestHandler<List<Integer>, Integer> {
	//static final Logger log = LoggerFactory.getLogger(SampleLambda.class);

	@Override
	public Integer handleRequest(List<Integer> input, Context context) {
		
		LambdaLogger logger = context.getLogger();
		logger.log("<<<<<SampleLambda.handleRequest() is Triggered>>>>>");
		
    	// Instantiate credentials
        final String AWS_ACCESS_KEY_ID = "AKIA5PTIVJ2P4DXQQU2E";
        final String AWS_SECRET_ACCESS_KEY = "ytvGCzQYflrvZI5guVwc2SdtVp2BHprf84WwRbo5";
        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);
		
		// Create an InvokeRequest with AWS function name and payload
        String payLoad = null;
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName("arn:aws:lambda:us-east-1:926858694303:function:CallingDynamoDB")
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
        
        logger.log("<<<<<SampleLambda.handleRequest() is Terminated>>>>>");
		
		//log.info("<<<<<Lambda Function is Triggered>>>>>");
		//log.info("S3 Event notification content: " + input.toJson());
		
        int sum = 0;
        for(Integer i : input) {
        	sum =+ i;
        }
		return sum;
	}

}