package com.bnpl.colc;

import java.util.Date;

import com.bnpl.colc.dto.BNPLAccountData;
import com.bnpl.colc.dto.BNPLPaymentData;
import com.bnpl.colc.dto.Instalment;
import com.bnpl.colc.fee.DefaultFeeApplicator;
import com.bnpl.colc.payment.PaymentProcessor;
import com.bnpl.sns.SMSNotificationService;

public class InstalmentsCollector {

	public void collectInstalments(BNPLAccountData bnplAccountData) {

		if (!bnplAccountData.isBadDebt()) {
			BNPLPaymentData paymentData = bnplAccountData.getBnplPaymentData();

			// check if account has been classified as bad debt already
			if (paymentData != null && !paymentData.isCeaseAutomatedCollections()) {

				// Instantiate tools object to access collections utility methods
				CollectionsTools tool = new CollectionsTools();
				// check if 2nd instalment is due
				Instalment instToBeCollected = paymentData.getInstalments()[1];
				long daysPassedAfterDueDate = tool.isInstalmentEligibleForCollection(instToBeCollected);
				if (daysPassedAfterDueDate == -1) {
					// check if 3rd instalment is due
					instToBeCollected = paymentData.getInstalments()[2];
					daysPassedAfterDueDate = tool.isInstalmentEligibleForCollection(instToBeCollected);
				}

				// if there is any due amount to be collected
				if (daysPassedAfterDueDate > -1) {
					executeAutoCollections(bnplAccountData, instToBeCollected, daysPassedAfterDueDate);
				}

			}
		}
	}

	private void executeAutoCollections(BNPLAccountData bnplAccountData, Instalment instToBeCollected,
			long daysPassedAfterDueDate) {

		// access loan payment data
		BNPLPaymentData paymentData = bnplAccountData.getBnplPaymentData();
		// if today is the due day
		if (daysPassedAfterDueDate == 0) {
			// trigger payment and notifications
			processPaymentAndNotify(bnplAccountData, instToBeCollected);
		} else if (daysPassedAfterDueDate == 1 || daysPassedAfterDueDate == 2 || daysPassedAfterDueDate == 3
				|| daysPassedAfterDueDate == 7) {
			// trigger payment only on 1st, 2nd, 3rd and 7th days after the due date.
			// apply default fee only if it was never applied before
			if (!paymentData.isDefaultFeeApplied()) {
				DefaultFeeApplicator feeApplicator = new DefaultFeeApplicator();
				feeApplicator.calculateDefaultFee(bnplAccountData, instToBeCollected);
			}
			// trigger payment and notifications
			processPaymentAndNotify(bnplAccountData, instToBeCollected);
		} else if (daysPassedAfterDueDate > 7) {
			// classify loan account as bad debt if payment is not captured beyond 7 days
			paymentData.setCeaseAutomatedCollections(true);
			paymentData.setAutomatedCollectionsCeaseDate(new Date());
		}

	}

	private void processPaymentAndNotify(BNPLAccountData bnplAccountData, Instalment instalmentToBeCollected) {
		System.out.println("inside process payment and notify");
		// 1. Revio call with totalAmount
		new PaymentProcessor().processPayment(bnplAccountData, instalmentToBeCollected);

		// 2. trigger AWS SNS
		String msgId = new SMSNotificationService().sendNotification(bnplAccountData.getPhoneNumber(),
				bnplAccountData.getBnplPaymentData().isDefaultFeeApplied());

		// 3. Save transaction states
		if (msgId != null && msgId.length() > 0) {
			// TODO: save sms notification under my account's notifications
		}
	}

}
