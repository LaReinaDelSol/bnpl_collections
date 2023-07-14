package com.bnpl.colc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.bnpl.colc.db.DynamoDBProcessor;
import com.bnpl.colc.dto.Instalment;
import com.bnpl.colc.dto.Instalment.InstalmentStatus;
import com.bnpl.colc.dto.LoanAccountData;
import com.bnpl.colc.dto.PaymentData;
import com.bnpl.colc.dto.PaymentStatus;
import com.bnpl.colc.fee.DefaultFeeApplicator;
import com.bnpl.colc.payment.PaymentProcessor;

public class CollectionsManager {
	
	private static DynamoDBProcessor DBCaller = new DynamoDBProcessor();

	private boolean collectInstalments(LoanAccountData bnplAccountData) throws ParseException {

		boolean collectionTriggered = false;
		if (!bnplAccountData.isBadDebt()) {
			PaymentData paymentData = bnplAccountData.getPaymentData();
			// check if account has been classified as bad debt already
			if (paymentData != null && !paymentData.isCeaseAutomatedCollections()) {

				// Instantiate tools object to access collections utility methods
				CollectionsTools tool = new CollectionsTools();
				int instalmentCount = 1;
				// check if 2nd instalment is due
				Instalment instToBeCollected = bnplAccountData.getInstalments().get(instalmentCount);
				long daysPassedAfterDueDate = tool.isInstalmentEligibleForCollection(instToBeCollected);
				if (daysPassedAfterDueDate == -1) {
					// check if 3rd instalment is due
					instToBeCollected = bnplAccountData.getInstalments().get(++instalmentCount);
					daysPassedAfterDueDate = tool.isInstalmentEligibleForCollection(instToBeCollected);
				}

				// if there is any due amount to be collected
				if (daysPassedAfterDueDate > -1) {
					collectionTriggered = executeAutoCollections(bnplAccountData, instToBeCollected, daysPassedAfterDueDate, instalmentCount);
				}
			} else {
				collectionTriggered = identifyBadDebtLoanAccount(bnplAccountData, null);
			}
		}
		return collectionTriggered;
	}
	
	public void triggerCollections(LoanAccountData bnplAccountData) throws ParseException {
		boolean accountUpdated = false;
		try {
			accountUpdated = collectInstalments(bnplAccountData);
		} finally {
			if(accountUpdated) {
				DBCaller.updateLoanAccountData(bnplAccountData);
			}
		}
		
	}

	private boolean executeAutoCollections(LoanAccountData bnplAccountData, Instalment instToBeCollected, long daysPassedAfterDueDate, int instalmentCount) {
		
		boolean accountUpdated = false;
		// access loan payment data
		PaymentData paymentData = bnplAccountData.getPaymentData();
		// if today is the due day
		if (daysPassedAfterDueDate == 0) {
			// trigger payment and notifications
			processPaymentAndNotify(bnplAccountData, instToBeCollected, instalmentCount);
			accountUpdated = true;
		} else {
			// apply default fee only if it was never applied before
			if (!paymentData.isDefaultFeeApplied()) {
				DefaultFeeApplicator feeApplicator = new DefaultFeeApplicator();
				feeApplicator.levyDefaultFee(bnplAccountData, instToBeCollected);
				accountUpdated = true;
			}
				
			if (daysPassedAfterDueDate == 1 || daysPassedAfterDueDate == 2 || daysPassedAfterDueDate == 3 || daysPassedAfterDueDate == 7) {
				// trigger payment and notifications
				processPaymentAndNotify(bnplAccountData, instToBeCollected, instalmentCount);
				accountUpdated = true;
			} else if (daysPassedAfterDueDate > 7) {
				
				identifyBadDebtLoanAccount(bnplAccountData, instToBeCollected);
						
				// stop automated payments beyond 7 days
				if(!paymentData.isCeaseAutomatedCollections()) {
					paymentData.setCeaseAutomatedCollections(true);
					paymentData.setAutomatedCollectionsCeaseDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()));
				}
				accountUpdated = true;
			}
		}
		return accountUpdated;
	}

	public boolean identifyBadDebtLoanAccount(LoanAccountData bnplAccountData, Instalment instToBeCollected) {
		
		if(instToBeCollected == null) {
			instToBeCollected = findOverdueInstalment(bnplAccountData);
		}

		if(instToBeCollected != null) {
			LocalDate dueDate = null;
			try {
				dueDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(instToBeCollected.getDueDate()).toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();
			} catch (ParseException pe) {
				System.err.println(pe.getMessage());
			}
	
			long bizDaysPassedAfterDueDate = new CollectionsTools().countBusinessDaysFromCurrentDay(dueDate);
			System.out.println("Number of business days passed after the due date is : " + bizDaysPassedAfterDueDate);
			
			if (bizDaysPassedAfterDueDate > 21) {
				DefaultFeeApplicator feeApplicator = new DefaultFeeApplicator();
				return feeApplicator.removeDefaultFee(bnplAccountData);
			}
		}
		return false;
	}

	public Instalment findOverdueInstalment(LoanAccountData bnplAccountData) {

		Instalment overdueIns = null;
			for(int i = 1; i<3; i++) {
				if(bnplAccountData.getInstalments().get(i).getInstallmentStatus().equals(InstalmentStatus.OVERDUE.name())) {
					overdueIns = bnplAccountData.getInstalments().get(i);
					break;
				}
			}
		
		return overdueIns;
	}

	private void processPaymentAndNotify(LoanAccountData bnplAccountData, Instalment instalmentToBeCollected, int instalmentCount) {

		System.out.println("inside process payment and notify");

		PaymentProcessor paymentProc = new PaymentProcessor();
		PaymentStatus status = paymentProc.processPayment(bnplAccountData, instalmentToBeCollected.getCurrencySymbol(), instalmentCount);
		
		if(status != null) {
			// when dueDate is current date and the payment fails, applying defaultFee post an unsuccessful payment
			if(!status.isTransactionStatus() && !bnplAccountData.getPaymentData().isDefaultFeeApplied()) {
				DefaultFeeApplicator feeApplicator = new DefaultFeeApplicator();
				feeApplicator.levyDefaultFee(bnplAccountData, instalmentToBeCollected);
			}
	
			bnplAccountData.getPaymentData().getPaymentStatusList().add(status);
		}
		
		// TODO : 2. trigger AWS SNS
		
		// 3. Save transaction states
		// TODO: save sms notification under my account's notifications
	}

}
