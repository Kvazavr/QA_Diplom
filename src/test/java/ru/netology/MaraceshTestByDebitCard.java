package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class MaraceshTestByDebitCard {
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
        DBUtils.preperedDB();
        open("http://localhost:8080");
    }

    @Test
    void shouldBeSuccessfulBuyTourDebit() {

        TourDescriptionPage.chooseCardPayment();
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
    @Test
    void nameInCyrillic() {

    }
    @Test
    void nameWithYo() {

    }
    @Test
    void nameWithHyphen() {

    }
    @Test
    void emptyFieldNumber(){

    }
    @Test
    void emptyFieldMonth(){

    }
    @Test
    void emptyFieldYear(){

    }
    @Test
    void emptyFieldName(){

    }
    @Test
    void emptyFieldCVV(){

    }
    @Test
    void declineNumber(){

    }
    @Test
    void notEnoughCharInNumber(){

    }
    @Test
    void lettersInNumber(){

    }
    @Test
    void specSymbolNumber(){

    }
    @Test
    void invalidMonth(){

//        DataHelper.getValidCardInfo().withMonth(DataHelper.getInvalidMonth());
    }
    @Test
    void lettersInMonth() {

    }

    @Test
    void specSymbolInMonth(){

    }
    @Test
    void inputOneNumberInMonth() {

    }
    @Test
    void oneLetterInName(){

    }
    @Test
    void specSymbolInName(){

    }
    @Test
    void numbersInName(){

    }
    @Test
    void lessThanCurrentYear(){

    }
    @Test
    void oneNumberInYear(){

    }
    @Test
    void specSymbolInYear(){

    }
    @Test
    void lettersInYear(){

    }
    @Test
    void lettersInCVV(){

    }
    @Test
    void oneNumberInCVV(){

    }
    @Test
    void specSymbolInCVV(){

    }


}

