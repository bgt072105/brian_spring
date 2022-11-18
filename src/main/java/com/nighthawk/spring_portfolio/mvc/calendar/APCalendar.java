package com.nighthawk.spring_portfolio.mvc.calendar;
import java.util.Date;
import java.util.Calendar;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year) {
        if(year%400==0){
            return true;
        }
        if(year%100==0){
            return false;
        }
        if(year%4==0){
            return true;
        }

        return false;
        }
        
    /** Returns the value representing the day of the week 
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    private static int firstDayOfYear(int year) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, Calendar.JANUARY);
    cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.get(Calendar.DAY_OF_WEEK)-1;
        }


    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    private static int dayOfYear(int month, int day, int year) {
        int dayNumber = 0;
        if(month==1){
            dayNumber+=day;
        }
        else if(month==2){
            dayNumber+=31+day;
        }
        else if(month==3){
            dayNumber+=31+28+day;
        }
        else if(month==4){
            dayNumber+=31+28+31+day;
        }
        else if(month==5){
            dayNumber+=31+28+31+30+day;
        }
        else if(month==6){
            dayNumber+=31+28+31+30+31+day;
        }
        else if(month==7){
            dayNumber+=31+28+31+30+31+30+day;
        }
        else if(month==8){
            dayNumber+=31+28+31+30+31+30+31+day;
        }
        else if(month==9){
            dayNumber+=31+28+31+30+31+30+31+31+day;
        }
        else if(month==10){
            dayNumber+=31+28+31+30+31+30+31+31+30+day;
        }
        else if(month==11){
            dayNumber+=31+28+31+30+31+30+31+31+30+31+day;
        }
        else if(month==12){
            dayNumber+=31+28+31+30+31+30+31+31+30+31+30+day;
        }
        if(month>2&&isLeapYear(year)){
            dayNumber+=1;
        }
        return dayNumber;
        }

    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
        int count = 0;
        for (int x = year1; x <= year2; x++){
            if (isLeapYear(x)){
                count++;
            }
        }
        return count;
        }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        int firstDayOfTheYear = firstDayOfYear(year);
        int dayOfTheYear = dayOfYear(month, day, year);
        return firstDayOfTheYear+dayOfTheYear%7-1;
        }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2022));
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(1, 1, 2022));

        // Public access modifiers
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
    }

}