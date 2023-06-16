package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class PaymentPage {
    private SelenideElement codeFieldTitle = $x("//h2/[contains(text(), 'Путешествие дня')]");
    private SelenideElement codeFieldTitleOfTour = $x("//h3[contains(text(), 'Мараккэш')]");
    private SelenideElement buttonToBuy = $("[data-test-id=action-verify]");button class="button__text"Купить
    private SelenideElement codeFieldTitleOfTour = $x("//*[contains(text(), 'Мараккэш')]");


    public DashboardPage() {
        codeFieldTitle.shouldBe(Condition.visible);
        codeFieldTitleOfTour.shouldBe(Condition.visible);

    }

}
