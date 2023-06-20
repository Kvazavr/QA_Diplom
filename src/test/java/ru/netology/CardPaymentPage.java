package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CardPaymentPage {
    SelenideElement titleCardPayment = $x("//h2/[contains(text(), 'Оплата по карте')]");
    static SelenideElement cardNumber = $x("//*[contains(text(), 'Номер карты')]/../span/input");
    static SelenideElement month = $x("//*[contains(text(), 'Месяц')]/../*/input");
    static SelenideElement year = $x("//*[contains(text(), 'Год')]");
    static SelenideElement ownerName = $x("//*[contains(text(), 'Владелец')]");
    static SelenideElement cvc = $x("//*[contains(text(), 'CVC/CVV)]");
    static SelenideElement continueButton = $x("//*[contains(text(), 'Продолжить')]");
    static SelenideElement successfulNotification = $(".notification__content").shouldHave(Condition.text("Операция одобрена банком"));

    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String name;
        private String cvv;
    }
    public static CardInfo getValidCardInfo() {

        return new CardInfo("4444 4444 4444 4441", "08", "24", "ELENA NEKRASOVA", "321");
    }
    public static CardInfo getInvalidNumberOfCard() {

        return new CardInfo("4444 4444 4444 4445", "08", "24", "ELENA NEKRASOVA", "321");
    }
    public static CardInfo getInvalidMonth() {

        return new CardInfo("4444 4444 4444 4441", "13", "24", "ELENA NEKRASOVA", "321");
    }
    public static CardInfo getInvalidYear() {

        return new CardInfo("4444 4444 4444 4441", "08", "22", "ELENA NEKRASOVA", "321");
    }
    public static CardInfo getInvalidName() {

        return new CardInfo("4444 4444 4444 4441", "08", "22", "123456++", "321");
    }
    public static CardInfo getInvalidCVV() {

        return new CardInfo("4444 4444 4444 4441", "08", "22", "ELENA NEKRASOVA", "+++");
    }
}




