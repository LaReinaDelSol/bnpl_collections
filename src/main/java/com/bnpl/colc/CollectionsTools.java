package com.bnpl.colc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.bnpl.colc.dto.Instalment;

public class CollectionsTools {
	public long daysPassedAfterDueDate(Date dueDate) {

		LocalDate currentLocalDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dueLocalDate = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		long diffDays = ChronoUnit.DAYS.between(dueLocalDate, currentLocalDate);

        System.out.println("Difference in days: " + diffDays);      
		return diffDays;
	}
	
	// returns difference of payment due date and current date. In case payment due date falls after current date, it returns -1
	public long isInstalmentEligibleForCollection(Instalment instalment) {
		long daysPassed = daysPassedAfterDueDate(instalment.getDueDate());
		if(daysPassed >= 0 && !instalment.isInstallmentStatus()) {
			return daysPassed;
		}
		return -1;
	}
	
	// method to count business days from passed date to current day. Monday to Friday is considered to be business days irrespective of holidays.	 
    public long countBusinessDaysFromCurrentDay(LocalDate pastDays) {
    	LocalDate startDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long totalDays = ChronoUnit.DAYS.between(pastDays, startDate);
        long weekends = countWeekends(pastDays, startDate);
        
        return totalDays - weekends;
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
