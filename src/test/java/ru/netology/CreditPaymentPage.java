package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreditPaymentPage {
    SelenideElement titleCardPayment = $x("//h2/[contains(text(), 'Кредит по данным карты')]");
    SelenideElement cardNumber = $x("//*[contains(text(), 'Номер карты')]/../span/input");
    SelenideElement month = $x("//*[contains(text(), 'Месяц')]/../*/input");
    SelenideElement year = $x("//*[contains(text(), 'Год')]");
    SelenideElement ownerName = $x("//*[contains(text(), 'Владелец')]");
    SelenideElement cvc = $x("//*[contains(text(), 'CVC/CVV)]");
    SelenideElement continueButton = $x("//*[contains(text(), 'Продолжить')]");
    SelenideElement successfulNotification = $(".notification__content").shouldHave(Condition.text("Операция одобрена банком"));
    SelenideElement unsuccessfulNotification = $(".notification__content").shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."));

    public CreditPaymentPage() {
        titleCardPayment.shouldBe(Condition.visible);
    }
}
