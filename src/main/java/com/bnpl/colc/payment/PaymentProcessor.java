package com.bnpl.colc.payment;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bnpl.colc.dto.LoanAccountData;
import com.bnpl.colc.dto.PaymentResponse;
import com.bnpl.colc.dto.PaymentStatus;

public class PaymentProcessor {

	public PaymentStatus processPayment(LoanAccountData bnplAccountData, String currencySymbol,
			int instalmentNumberToBeCollected) {

		PaymentResponse res = processPayment(bnplAccountData.getRefID(), bnplAccountData.getRefDate(), new int[] { instalmentNumberToBeCollected });
		PaymentStatus status = null;
		if (res != null) {
			status = new PaymentStatus();
			status.setAmount(res.getAmount());
			status.setCurrencySymbol(currencySymbol);
			status.setPaymentTransactionDate(res.getTransactionDate());
			status.setTransactionDesc("Auto Debit Instalment " + ++instalmentNumberToBeCollected);
			status.setTransactionStatus(res.isStatus());
		}
		return status;
	}

	private PaymentResponse processPayment(String refID, String refDate, int[] instalmentNumberToBeCollected) {

		boolean paymentSuccess = true;

		PaymentResponse res = new PaymentResponse();
		res.setAmount(174.99);
		res.setStatus(paymentSuccess);
		res.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()));
		res.setTransactionRefID("transactionId");

		return res;

	}
}