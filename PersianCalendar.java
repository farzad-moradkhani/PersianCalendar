import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This library is for converting date to Persian date system.
 * It also includes leap years.
 * Features: Returns Day of the week as Persian.
 * How to use: It takes a String in the constructor in yyyy-mm-dd format like 2024-01-01
 * (optional:You can skip including String in the constructor,and it will take the systems current time as an input).
 * Then it returns like 1403-01-01
 * Contact me if you found any issues.
 * By Farzad Moradkhani
 * */
public class PersianCalendar {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    LocalDate inputDate;
    private String strMonth="";
    private String strDate="";
    private String strDay="";
    public PersianCalendar (){
        inputDate = LocalDate.now();
        calculateDate();
    }
    public PersianCalendar (String s){
        inputDate = LocalDate.parse(s, formatter);
        calculateDate();
    }
    public void setDate(String date){
        inputDate = LocalDate.parse(date,formatter);
        calculateDate();
    }
    private void calculateDate(){
        LocalDate dateTimeBC = inputDate;

        int miladi_year  = dateTimeBC.getYear();
        int miladi_month = dateTimeBC.getMonthValue();
        int miladi_day = dateTimeBC.getDayOfMonth();

        int year=0;
        int month=0;
        int date=0;



        int differenceBetweenDays = 0;
        int[] notLeapYear = new int[]{0,31,59,90,120,151 , 181 , 212 , 243 , 273 , 304 , 334};
        int[] isLeapYear = new int[]{0 , 31 , 60 , 91 , 121 , 152 , 182 , 213 , 244 , 274 , 305 , 335};
        if(((year)%4)!=0){
            date = notLeapYear[miladi_month-1]+miladi_day;
            if(date>79){
                date=date-79;
                if(date<=186){
                    switch (date%31){
                        case 0:
                            month=date/31;
                            date=31;
                            break;
                        default :
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year=miladi_year-621;
                }else {
                    date = date - 186;

                    switch(date % 30){
                        case 0 :
                            month = (date / 30 ) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30 ) + 7;
                            date = (date % 30);
                            break;
                    }
                    year = miladi_year - 621;
                }
            }else{
                if((miladi_year > 1996) && (miladi_year % 4) == 1 ){
                    differenceBetweenDays = 11;
                }else{
                    differenceBetweenDays = 10;
                }
                date = date + differenceBetweenDays;

                switch(date % 30){
                    case 0 :
                        month = (date / 30) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladi_year - 622;
            }
        }
        else{
            date = isLeapYear[miladi_month - 1] + miladi_day;

            if(miladi_year >= 1996){
                differenceBetweenDays = 80;
            }else{
                differenceBetweenDays = 70;
            }
            if(date > differenceBetweenDays){
                date = date - differenceBetweenDays;

                if(date <= 186){
                    switch (date % 31) {
                        case 0 :
                            month = (date / 31);
                            date = 31;
                            break;
                        default:
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year = miladi_year - 621;
                }else{
                    date = date - 186;

                    switch( date % 30){
                        case 0 :
                            month = (date / 30 ) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30 ) + 7;
                            date = (date % 30 );
                            break;
                    }
                    year = miladi_year - 621;
                }
            }else{
                date = date + 10;

                switch (date % 30){
                    case 0 :
                        month = (date / 30 ) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30 ) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladi_year - 622;
            }
        }
        switch(month) {
            case 1:
                strMonth = "فرودین";
                break;
            case 2:
                strMonth = "اردیبهشت";
                break;
            case 3:
                strMonth = "خرداد";
                break;
            case 4:
                strMonth = "تیر";
                break;
            case 5:
                strMonth = "مرداد";
                break;
            case 6:
                strMonth = "شهریور";
                break;
            case 7:
                strMonth = "مهر";
                break;
            case 8:
                strMonth = "آبان";
                break;
            case 9:
                strMonth = "آذر";
                break;
            case 10:
                strMonth = "دی";
                break;
            case 11:
                strMonth = "بهمن";
                break;
            case 12:
                strMonth = "اسفند";
                break;
            default:
                break;
        }


        String formattedMonth = String.format("%02d", month);
        String formattedDay = String.format("%02d", date);
        strDate  = String.valueOf(year) + "-" +  formattedMonth+ "-" + formattedDay;



    }
    public String getMonth(){
        return strMonth;
    }
    public String getDayOfWeek(){
        LocalDate localDate = inputDate;
        String day = localDate.getDayOfWeek().name();
        switch (day){
            case "SATURDAY":
                day="شنبه";
                break;
            case "SUNDAY":
                day="یکشنبه";
                break;
            case "MONDAY":
                day="دوشنبه";
                break;
            case "TUESDAY":
                day="سه شنبه";
                break;
            case "WEDNESDAY":
                day="چهارشنبه";
                break;
            case "THURSDAY":
                day="پنج شنبه";
                break;
            case "FRIDAY":
                day="جمعه";
                break;


        }
        return day;
    }
    public String getDate(){
        return strDate;
    }

}