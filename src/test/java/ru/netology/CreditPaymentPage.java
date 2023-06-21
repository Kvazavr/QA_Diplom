package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreditPaymentPage {
    SelenideElement titleCardPayment = $x("//h2/[contains(text(), 'Кредит по данным карты')]");
    static SelenideElement cardNumber = $x("//*[contains(text(), 'Номер карты')]/../span/input");
    static SelenideElement month = $x("//*[contains(text(), 'Месяц')]/../*/input");
    static SelenideElement year = $x("//*[contains(text(), 'Год')]");
    static SelenideElement ownerName = $x("//*[contains(text(), 'Владелец')]");
    static SelenideElement cvc = $x("//*[contains(text(), 'CVC/CVV)]");
    static SelenideElement continueButton = $x("//*[contains(text(), 'Продолжить')]");
    static SelenideElement successfulNotification = $(".notification__content").shouldHave(Condition.text("Операция одобрена банком"));
    public CreditPaymentPage(){
        titleCardPayment.shouldBe(Condition.visible);
    }
}
