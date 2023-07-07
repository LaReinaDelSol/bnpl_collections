package com.bnpl.colc;

import com.bnpl.colc.dto.BNPLAccountData;

public class TriggerCollections {

	public static void main(String[] args) {
		
		System.out.println("collections triggered");
		BNPLAccountData bnplAccountData = new BNPLAccountFactory().createBNPLAccount();
		InstalmentsCollector collector = new InstalmentsCollector();
		
		collector.collectInstalments(bnplAccountData);

	}

}
