package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TourDescriptionPage {
    SelenideElement title = $x("//*[contains(text(), 'Путешествие дня')]");
    SelenideElement tourName = $x("//*[contains(text(), 'Марракэш')]");
    SelenideElement buttonBuy = $x("//*[contains(text(), 'Купить')]");
    SelenideElement buttonBuyInCredit = $x("//*[(text() = 'Купить в кредит')]");
    public TourDescriptionPage(){
        title.shouldBe(Condition.visible);
        tourName.shouldBe(Condition.visible);
        buttonBuy.shouldBe(Condition.visible);
        buttonBuyInCredit.shouldBe(Condition.visible);
    }

    public CardPaymentPage chooseCardPayment() {
        buttonBuy.click();
        return new CardPaymentPage();
    }
    public CreditPaymentPage chooseCreditPayment() {
        buttonBuyInCredit.click();
        return new CreditPaymentPage();
    }

}
