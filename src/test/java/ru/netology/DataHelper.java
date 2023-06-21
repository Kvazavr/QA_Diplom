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
        String number = "4444 4444 4444 444";
        return number;
    }

    public static String cardOwnerName() {
        String name = "ELENA NEKRASOVA";
        return name;
    }
    public static String invalidMonth() {

        return "13";
    }

    public static String invalidYear() {
        return "22";
    }

    public static String invalidName() {

        return "123456++";
    }

    public static String invalidCVV() {

        return "+++";
    }

    public static CardInfo getValidCardInfo() {

        return new CardInfo(cardNumberApproved(), "08", "24", cardOwnerName(), "321");
    }

}
