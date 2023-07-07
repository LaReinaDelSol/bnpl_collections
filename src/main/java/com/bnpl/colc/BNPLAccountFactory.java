package com.bnpl.colc;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.bnpl.colc.dto.BNPLAccountData;
import com.bnpl.colc.dto.BNPLPaymentData;
import com.bnpl.colc.dto.Instalment;

public class BNPLAccountFactory {
	
	public BNPLAccountData createBNPLAccount() {
		BNPLAccountData account = new BNPLAccountData();
		
		BNPLPaymentData paymnetData = account.getBnplPaymentData();
		try {
			paymnetData.setInstalments(createInstalments());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// set account as bad debt
		//account.setBadDebtClassied(true);
		account.getBnplPaymentData().getInstalments()[1].setInstallmentStatus(true);
		
		return account;
		
	}
	
	
	public Instalment[] createInstalments() throws ParseException {
		
		// int amount, String currencySymbol, Date dueDate, int failedAttempts, boolean installmentStatus, Date paidOn, String transactionRef
		
		Instalment ins1 = new Instalment(350, "R", new SimpleDateFormat("dd-MM-yyyy").parse("25-05-2023"), 0,
			true, new SimpleDateFormat("dd-MM-yyyy").parse("02-01-24"), String.valueOf(Math.random()));
		
		Instalment ins2 = new Instalment(150, "R", new SimpleDateFormat("dd-MM-yyyy").parse("21-06-2023"), 0,
				true, new SimpleDateFormat("dd-MM-yyyy").parse("02-01-24"), String.valueOf(Math.random()));
		
		Instalment ins3 = new Instalment(150, "R", new SimpleDateFormat("dd-MM-yyyy").parse("25-07-2023"), 0,
				true, new SimpleDateFormat("dd-MM-yyyy").parse("02-01-24"), String.valueOf(Math.random()));
		
		return new Instalment[] {ins1, ins2, ins3};
				
	}

}
