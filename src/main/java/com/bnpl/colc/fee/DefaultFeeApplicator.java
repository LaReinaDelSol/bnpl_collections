package com.bnpl.colc.fee;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bnpl.colc.dto.LoanAccountData;
import com.bnpl.colc.dto.PaymentData;
import com.bnpl.colc.dto.Instalment;

public class DefaultFeeApplicator {

	private static final double DEFAULT_FEE = 75.0;

	public void levyDefaultFee(LoanAccountData bnplAccountData, Instalment instalmentToBeCollected) {

		PaymentData paymentData = bnplAccountData.getPaymentData();
		if (!paymentData.isDefaultFeeApplied()) {
			instalmentToBeCollected.setFeeAmount(DEFAULT_FEE);
			paymentData.setDefaultFeeApplied(true);

		}
	}

	public boolean removeDefaultFee(LoanAccountData bnplAccountData) {
		PaymentData paymentData = bnplAccountData.getPaymentData();
		if (paymentData.isDefaultFeeApplied()) {
			for (Instalment ins : bnplAccountData.getInstalments()) {
				ins.setFeeAmount(0);
			}
			paymentData.setDefaultFeeApplied(false);
			
			bnplAccountData.setBadDebt(true);
			bnplAccountData.setBadDebtDeclarationDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()));
			return true;
		}
		return false;
	}
}
