package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CardPaymentPage {
    SelenideElement titleCardPayment = $x("//h3/[contains(text(), 'Оплата по карте')]");
    SelenideElement cardNumber = $x("//*[contains(text(), 'Номер карты')]/../*/input");
    SelenideElement month = $x("//*[contains(text(), 'Месяц')]/../*/input");
    SelenideElement year = $x("//*[contains(text(), 'Год')]/../*/input");
    SelenideElement ownerName = $x("//*[contains(text(), 'Владелец')]/../*/input");
    SelenideElement cvc = $x("//*[contains(text(), 'CVC/CVV')]/../*/input");
    SelenideElement button = $x("//*[text()[contains(., 'Продолжить')]]");
//    SelenideElement button = $(".button__text" х).shouldHave(Condition.text("Продолжить"));

//    SelenideElement unsuccessfulNotification = $(".notification__content").shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."));

//    public CardPaymentPage() {
//        titleCardPayment.shouldBe(Condition.visible);
//    }

    public void paySuccess(CardInfo data) {
        setElementValue(cardNumber, data.getNumber());
        setElementValue(month, data.getMonth());
        setElementValue(year, data.getYear());
        setElementValue(ownerName, data.getName());
        setElementValue(cvc, data.getCvv());
        button.click();
//        continueButton.click();
//        successfulNotification.shouldBe(Condition.visible, Duration.ofSeconds(7));
    }
    private void setElementValue(SelenideElement element, String value) {
        element.click();
        element.setValue(value);
    }
    public void approved() {
        SelenideElement successfulNotification = $(".notification__content").shouldHave(Condition.text("Операция одобрена Банком"));
        successfulNotification.shouldBe(Condition.visible, Duration.ofMillis(5000));
    }


}




