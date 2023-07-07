package com.bnpl.colc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.bnpl.colc.dto.BNPLAccountData;
import com.bnpl.colc.dto.BNPLPaymentData;
import com.bnpl.colc.fee.DefaultFeeApplicator;

public class BadDebtIdentificator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean identifyBadDebtLoanAccount(BNPLAccountData bnplAccountData) {

		// Access loan payment data
		BNPLPaymentData paymentData = bnplAccountData.getBnplPaymentData();

		// check if automated collections are stopped on the account already as all
		// automated collections stop after 7 days from any unpaid instalment due date
		if (paymentData != null && paymentData.isCeaseAutomatedCollections()) {

			// get the date when automated collections were stopped
			LocalDate autoPaymentCeaseDate = paymentData.getAutomatedCollectionsCeaseDate().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			// calculate number of days passed since automated payments were stopped
			long daysPassedSinceCollectionsStopped = new CollectionsTools()
					.countBusinessDaysFromCurrentDay(autoPaymentCeaseDate);

			// since automated collections are stopped 7 days after any unpaid instalment
			// due date, validating against only 21 days from current date would suffice to
			// verify if 20 working days have passed for any defaulted account to be termed
			// as bad debt
			if (daysPassedSinceCollectionsStopped > 21) {
				DefaultFeeApplicator feeApplicator = new DefaultFeeApplicator();
				feeApplicator.removeDefaultFee(bnplAccountData);
				bnplAccountData.setBadDebt(true);
				bnplAccountData.setBadDebtDeclarationDate(new Date());
			}
		}

		return true;
	}

}
