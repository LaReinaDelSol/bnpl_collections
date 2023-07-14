package com.bnpl.colc;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bnpl.colc.dto.LoanAccountData;

public class ExecCollectionsLambda implements RequestHandler<LoanAccountData, LoanAccountData> {
	 
	@Override
	public LoanAccountData handleRequest(LoanAccountData account, Context context) {
		
		LambdaLogger logger = context.getLogger();
		logger.log("<<<<< ExecCollectionsLambda.handleRequest() is Triggered >>>>>");

		TriggerCollections trigger = new TriggerCollections();
		trigger.triggerCollections();
        
        logger.log("<<<<<ExecCollectionsLambda.handleRequest() is Terminated>>>>>");
		
		return account;
	}

}
