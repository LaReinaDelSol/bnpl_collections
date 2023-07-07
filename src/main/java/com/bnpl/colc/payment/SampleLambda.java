package com.bnpl.colc.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.event.S3EventNotification;

public class SampleLambda implements RequestHandler<S3EventNotification, String> {
	static final Logger log = LoggerFactory.getLogger(SampleLambda.class);

	@Override
	public String handleRequest(S3EventNotification input, Context context) {
		
		log.info("<<<<<Lambda Function is Triggered>>>>>");
		log.info("S3 Event notification content: " + input.toJson());
		
		return input.toJson();
	}

}
