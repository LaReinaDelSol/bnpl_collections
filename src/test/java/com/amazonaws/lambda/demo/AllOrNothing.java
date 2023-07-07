package com.amazonaws.lambda.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class AllOrNothing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello world");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		try {
			date = dateFormat.parse("02-01-24");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);

		System.out.println("--------------------------");

		// Convert the Date object to LocalDate
		LocalDate currentLocalDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(currentLocalDate);

		// Create a LocalDate object representing a specific date
		LocalDate targetDate = LocalDate.of(2023, 6, 20);
		System.out.println(targetDate);

		// Compare the target date with the current date
		int comparisonResult = targetDate.compareTo(currentLocalDate);

		// Display the comparison result
		if (comparisonResult < 0) {
			System.out.println("The target date is before the current date.");
		} else if (comparisonResult > 0) {
			System.out.println("The target date is after the current date.");
		} else {
			System.out.println("The target date is the same as the current date.");
		}

		LocalDate date1 = LocalDate.of(2023, 6, 1); // First date
		LocalDate date2 = LocalDate.of(2023, 6, 10); // Second date

		Period period = Period.between(date1, date2);
		int diffDays = period.getDays();

		// System.out.println("Difference in days: " + diffDays);

		
		LocalDate pastDays = LocalDate.of(2023, 8, 1);
		long workingDays = countWorkingDays(pastDays);

/*		while (currentLocalDate.isAfter(pastDays)) {
			if (currentLocalDate.getDayOfWeek() != DayOfWeek.SATURDAY
					&& currentLocalDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
				workingDays++;
			}
			currentLocalDate = currentLocalDate.plusDays(1);
		}*/
		System.out.println("working days : " + workingDays);
	}


    
    public static long countWorkingDays(LocalDate pastDays) {
    	LocalDate startDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long totalDays = ChronoUnit.DAYS.between(pastDays, startDate);
        long weekends = countWeekends(pastDays, startDate);
        
        return totalDays - weekends;
    }

    private static long countWeekends(LocalDate startDate, LocalDate endDate) {
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
