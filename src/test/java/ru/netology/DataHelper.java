package ru.netology;

public class DataHelper {
    private DataHelper() {

    }

    public static String cardNumberApproved() {

        String number = "4444 4444 4444 4441";
        return number;
    }

    public static String cardNumberDeclined() {

        String number = "4444 4444 4444 4442";
        return number;
    }

    public static String invalidCardNumber() {
        String number = "4444 4444 4444 4445";
        return number;
    }

    public static String cardOwnerName() {
        String name = "ELENA NEKRASOVA";
        return name;
    }
}
