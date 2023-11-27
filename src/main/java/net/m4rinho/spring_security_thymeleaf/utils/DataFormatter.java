package net.m4rinho.spring_security_thymeleaf.utils;

import java.time.LocalDateTime;

public class DataFormatter {
	
	public static String formatDateToString(LocalDateTime localDateTime){
		int hour = localDateTime.getHour();
		int minute = localDateTime.getMinute();
		
		int day = localDateTime.getDayOfMonth();
		int month = localDateTime.getMonthValue();
		int year = localDateTime.getYear();
		
		return String.format("Created at: %d/%d/%d at %d:%d", day,month,year,hour,minute);
	}
}
