package com.wipro.eshop.util; 
 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
 
public class DateUtil { 
 
    public static Date convertToDate(String dateStr) { 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy"); 
        try { 
            return sdf.parse(dateStr); 
        } catch (ParseException e) { 
            System.out.println(" Invalid date format. Use dd/mm/yyyy"); 
            return null; 
        } 
    } 
    public static String convertToString(Date date) { 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy"); 
        return sdf.format(date); 
    }
}
