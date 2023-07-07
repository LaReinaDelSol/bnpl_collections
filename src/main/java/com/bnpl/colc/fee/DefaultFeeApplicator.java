package com.bnpl.colc.fee;

import com.bnpl.colc.dto.BNPLAccountData;
import com.bnpl.colc.dto.BNPLPaymentData;
import com.bnpl.colc.dto.Instalment;

public class DefaultFeeApplicator {
	
	private static final int DEFAULT_FEE = 75;

	public void calculateDefaultFee(BNPLAccountData bnplAccountData, Instalment instalmentToBeCollected) {
		
		BNPLPaymentData paymentData = bnplAccountData.getBnplPaymentData();	
		if(!paymentData.isDefaultFeeApplied()) {
		instalmentToBeCollected.setFeeAmount(DEFAULT_FEE);
		paymentData.setDefaultFeeApplied(true);
		
		}
	}
	
	public void removeDefaultFee(BNPLAccountData bnplAccountData) {
		BNPLPaymentData paymentData = bnplAccountData.getBnplPaymentData();	
		if(paymentData.isDefaultFeeApplied()) {
			for(Instalment ins : paymentData.getInstalments())
				ins.setFeeAmount(0);
			paymentData.setDefaultFeeApplied(false);		
		}
	}
}
	