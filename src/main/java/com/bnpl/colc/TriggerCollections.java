package com.bnpl.colc;

import java.text.ParseException;

import com.bnpl.colc.db.DynamoDBProcessor;
import com.bnpl.colc.dto.LoanAccountData;

public class TriggerCollections {

	public void triggerCollections() {

		System.out.println("--- Collections triggered ---");

		LoanAccountData account = DynamoDBProcessor.queryByItemID("12ffc879-c871-5370-b408-0ffbbe837bf6", "1788649562853");
		CollectionsManager collector = new CollectionsManager();
		try {
			collector.triggerCollections(account);
		} catch (ParseException pe) {
			System.err.println(pe.getMessage());
		}
		
		System.out.println("--- Collections process terminated ---");

	}
}