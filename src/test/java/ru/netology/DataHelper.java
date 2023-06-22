package ru.netology;

public class DataHelper {
    private DataHelper() {

    }

    public static String cardNumberApproved() {

        String number = "4444 4444 4444 4441";
        return number;
    }

    public static String cardNumberDeclined() {

//        String number = "4444 4444 4444 4442";
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
    public static String lettersInCardNumber() {
        String number = "fghfgh";
        return number;
    }
    public static String specSymbolInCardNumber() {
        String number = "++---";
        return number;
    }

    public static String cardOwnerName() {
        String name = "ELENA NEKRASOVA";
        return name;
    }

    public static String invalidMonth() {

        return "13";
    }
    public static String oneNumberInMonth() {

        return "3";
    }

    public static String invalidYear() {

        return "22";
    }

    public static String numbersName() {

        return "123456";
    }

    public static String cyrillicName() {

        return "Некрасова Елена";
    }

    public static String specSymbolName() {

        return "+++---";
    }

    public static String invalidFormatName() {

        return "fvdfgvkds";
    }

    public static String nameWithHyphen() {
        return "Mari-Ann";
    }

    public static String invalidCVV() {

        return "+as";
    }

    public static CardInfo getValidCardInfo() {

        return new CardInfo(cardNumberApproved(), "08", "24", cardOwnerName(), "321");
    }


    public static CardInfo getInvalidCardInfo() {

        return new CardInfo(invalidCardNumber(), "08", "24", cardOwnerName(), "321");
    }
    public static CardInfo getDeclinedCardInfo() {

        return new CardInfo(cardNumberDeclined(), "08", "24", cardOwnerName(), "321");
    }
    public static CardInfo getNotEnoughNumbers() {

        return new CardInfo(notEnoughInCardNumber(), "08", "24", cardOwnerName(), "321");
    }

    public static CardInfo getCyrillicNameCardInfo() {

        return new CardInfo(cardNumberApproved(), "08", "24", cyrillicName(), "321");
    }

    public static CardInfo getNameWithHyphen() {
        return new CardInfo(cardNumberApproved(), "08", "24", nameWithHyphen(), "321");
    }
    public static CardInfo getNameWithNumbers() {
        return new CardInfo(cardNumberApproved(), "08", "24", numbersName(), "321");
    }
    public static CardInfo getNameWithSpecSymbol() {
        return new CardInfo(cardNumberApproved(), "08", "24", specSymbolName(), "321");
    }
    public static CardInfo getNameWithInvalidFormat() {
        return new CardInfo(cardNumberApproved(), "08", "24", invalidFormatName(), "321");
    }
    public static CardInfo getNumberEmpty() {
        return new CardInfo("", "08", "24", cardOwnerName(), "321");
    }
    public static CardInfo getMonthEmpty() {
        return new CardInfo(cardNumberApproved(), "", "24", cardOwnerName(), "321");
    }
    public static CardInfo getYearEmpty() {
        return new CardInfo(cardNumberApproved(), "08", "", cardOwnerName(), "321");
    }
    public static CardInfo getOwnerEmpty() {
        return new CardInfo(cardNumberApproved(), "08", "24", "", "321");
    }
    public static CardInfo getCVVEmpty() {
        return new CardInfo(cardNumberApproved(), "08", "24", cardOwnerName(), "");
    }
    public static CardInfo getInvalidYear() {
        return new CardInfo(cardNumberApproved(), "08", invalidYear(), cardOwnerName(), "321");
    }
    public static CardInfo getInvalidCVV() {
        return new CardInfo(cardNumberApproved(), "08", "28", cardOwnerName(), invalidCVV());
    }
    public static CardInfo getInvalidMonth() {
        return new CardInfo(cardNumberApproved(), invalidMonth(), "28", cardOwnerName(), "321");
    }
    public static CardInfo getOneNumberInMonth() {
        return new CardInfo(cardNumberApproved(), getOneNumberInMonth(), "28", cardOwnerName(), "321");
    }



}
