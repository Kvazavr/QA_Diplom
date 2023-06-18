package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class MaraceshTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBeSuccessfulBuyTourDebit() {
        $("h2").shouldBe(Condition.text("Путешествие дня"));
        $("h3").shouldBe(Condition.text("Марракэш"));
        $x("//*[contains(text(), 'Купить')]").click();
        $x("//*[contains(text(), 'Оплата по карте')]").shouldBe(Condition.visible);
        SelenideElement cardNumber = $x("//*[contains(text(), 'Номер карты')]/../span/input");
        cardNumber.click();
        cardNumber.setValue("4444 4444 4444 4441");
        $x("//*[contains(text(), 'Месяц')]/../*/input").click();
//        setValue("08");
        $x("//*[contains(text(), 'Год')]").setValue("31");
        $x("//*[contains(text(), 'Владелец')]").setValue("Елена Некрасова");
        $x("//*[contains(text(), 'CVC/CVV)]").setValue("441");
        $x("//*[contains(text(), 'Продолжить')]").click();
        $(".notification__content").shouldHave(Condition.text("Операция одобрена банком"), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

//        $("[data-test-id=date]").click();
//        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
//        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
//        $("[data-test-id=date] [value]").setValue(firstMeetingDate);
//        $("[name = name]").setValue(generateName());
//        $("[name = phone]").setValue(DataGenerator.generatePhone());
//        $("[data-test-id=agreement]").click();
//        $(".button__text").click();
//        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate)).shouldBe(Condition.visible);
//        $("[data-test-id=date]").click();
//        $("[data-test-id=date] [value]").sendKeys(Keys.CONTROL + "a");
//        $("[data-test-id=date] [value]").sendKeys(Keys.BACK_SPACE);
//        $("[data-test-id=date] [value]").setValue(secondMeetingDate);
//        $(".button__text").click();
//        $x("//*[contains(text(), 'Необходимо подтверждение')]").shouldBe(Condition.visible);
//        $x("//*[contains(text(), 'Перепланировать')]").click();
//        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate)).shouldBe(Condition.visible);

    }

