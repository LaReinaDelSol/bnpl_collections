package com.bnpl.colc;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.bnpl.colc.dto.BNPLAccountData;

public class ExecCollectionsLambda implements RequestHandler<S3EventNotification, String> {
	
	@Override
	public String handleRequest(S3EventNotification input, Context context) {
		
		LambdaLogger logger = context.getLogger();
		logger.log("<<<<<ExecCollectionsLambda.handleRequest() is Triggered>>>>>");
		System.out.println("----logging with SOP----");
		BNPLAccountData bnplAccountData = new BNPLAccountFactory().createBNPLAccount();
		InstalmentsCollector collector = new InstalmentsCollector();
		
		collector.collectInstalments(bnplAccountData);
		
		logger.log("<<<<<ExecCollectionsLambda.handleRequest()t is Terminated>>>>>");
		
		return null;
	}

}
