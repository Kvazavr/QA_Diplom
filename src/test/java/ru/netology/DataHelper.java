package ru.netology;

public class DataHelper {
    private DataHelper() {

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
        String name = "ELENA NEKRASOVA";
        return name;
    }


    public static CardInfo getValidCardInfo() {

        return new CardInfo(cardNumberApproved(), "08", "24", holder(), "321");
    }


}
