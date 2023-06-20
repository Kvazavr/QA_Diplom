package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

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

        DataHelper.getValidCardInfo().withMonth(DataHelper.getInvalidMonth());
        CardPaymentPage.cardNumber.click();
        CardPaymentPage.cardNumber.setValue("4444 4444 4444 4441");
        CardPaymentPage.month.click();
        CardPaymentPage.month.setValue("08");
        CardPaymentPage.year.click();
        CardPaymentPage.year.setValue("24");
        CardPaymentPage.ownerName.click();
        CardPaymentPage.ownerName.setValue("ELENA NEKRASOVA");
        CardPaymentPage.cvc.click();
        CardPaymentPage.cvc.setValue("441");
        CardPaymentPage.continueButton.click();
        CardPaymentPage.successfulNotification.shouldBe(Condition.visible, Duration.ofSeconds(7));
    }
    @Test
    void testDB() {
        PaymentEntity paymentEntity = DBUtils.paymentEntity();
        Assertions.assertEquals("APPROVED", paymentEntity.getStatus());
        Assertions.assertEquals(4500000, paymentEntity.getAmount());
    }


}

