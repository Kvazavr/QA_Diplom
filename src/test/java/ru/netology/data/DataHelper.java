package ru.netology.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    private DataHelper() {

    }
    public static String generateDate(long addYear, String pattern) {
        return LocalDate.now().plusYears(addYear).format(DateTimeFormatter.ofPattern(pattern));
    }
    public static String validYear() {
       return generateDate(5, "YY");
    }
    public static String validMonth() {
        return generateDate(5, "MM");
    }

    public static String cardNumberApproved() {

        String number = "4444 4444 4444 4441";
        return number;
    }

    public static String cardNumberDeclined() {

        return "4444 4444 4444 4442";
    }

    public static String invalidCardNumber() {
        String number = "4444 4444 4444 4445";
        return number;
    }

    public static String notEnoughInCardNumber() {
        String number = "4444 4444 4444 444";
        return number;
    }

    public static Integer amount() {

        return 4500000;
    }

    public static String holder() {
        String name = "ELENA IVANOVA";
        return name;
    }


    public static CardInfo getValidCardInfo() {

        return new CardInfo(cardNumberApproved(), validMonth(), validYear(), holder(), "321");
    }


}
