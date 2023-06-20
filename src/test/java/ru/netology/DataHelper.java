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

    public static CardInfo getValidCardInfo() {

        return new CardInfo(cardNumberApproved(), "08", "24", cardOwnerName(), "321");
    }

    public static CardInfo getInvalidNumberOfCard() {

        CardInfo result = getValidCardInfo();
        result.setNumber(cardNumberDeclined());
        return result;
    }

    public static String getInvalidMonth() {

        return "13";
    }

    public static String getInvalidYear() {
        return "22";
    }

    public static String getInvalidName() {

        return "123456++";
    }

    public static String getInvalidCVV() {

        return "+++";
    }

}
