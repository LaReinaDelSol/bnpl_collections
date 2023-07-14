package com.bnpl.colc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.bnpl.colc.dto.Instalment;
import com.bnpl.colc.dto.Instalment.InstalmentStatus;

public class CollectionsTools {
	public long daysPassedAfterDueDate(Date dueDate) {

		LocalDate currentLocalDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dueLocalDate = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		long diffDays = ChronoUnit.DAYS.between(dueLocalDate, currentLocalDate);

		return diffDays;
	}
	
	// returns difference of payment due date and current date. In case payment due date falls after current date, it returns -1
	public long isInstalmentEligibleForCollection(Instalment instalment) throws ParseException {

		long daysPassed = daysPassedAfterDueDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(instalment.getDueDate()));
		
		System.out.println("Difference of number of days between due date and current date is : " + daysPassed);
		if (daysPassed >= 0 && (instalment.getInstallmentStatus().equals(InstalmentStatus.PENDING.name()))
				|| instalment.getInstallmentStatus().equals(InstalmentStatus.OVERDUE.name())) {
			
			return daysPassed;
		}
		return -1;
	}
	
	// method to count business days from passed date to current day. Monday to Friday is considered to be business days irrespective of holidays.	 
    public long countBusinessDaysFromCurrentDay(LocalDate pastDays) {
    	LocalDate startDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long totalDays = ChronoUnit.DAYS.between(pastDays, startDate);
        long weekendDays = countWeekends(pastDays, startDate);
        
        return totalDays - weekendDays;
    }

    private long countWeekends(LocalDate startDate, LocalDate endDate) {
        long weekends = 0;
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                weekends++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return weekends;
    }
}
