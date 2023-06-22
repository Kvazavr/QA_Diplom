package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;

public class MaraceshTestByDebitCard {
    TourDescriptionPage tour;

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
        tour = new TourDescriptionPage();
    }

    @Test
    void shouldBeSuccessfulBuyTourDebit() {

        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getValidCardInfo());
        card.approved();
    }

    @Test
    void testDB() {
        PaymentEntity paymentEntity = DBUtils.paymentEntity();
        Assertions.assertEquals("APPROVED", paymentEntity.getStatus());
        Assertions.assertEquals(4500000, paymentEntity.getAmount());
    }

    @Test
    void shouldBeDeclinedBuyTourDebit() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getDeclinedCardInfo());
        card.declined();
    }

    @Test
    void emptyFieldNumber() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getNumberEmpty());
        card.requiredFieldNotification();
    }

    @Test
    void emptyFieldMonth() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getMonthEmpty());
        card.requiredFieldNotification();

    }

    @Test
    void emptyFieldYear() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getYearEmpty());
        card.requiredFieldNotification();

    }

    @Test
    void emptyFieldName() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getOwnerEmpty());
        card.requiredFieldNotification();

    }

    @Test
    void emptyFieldCVV() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getCVVEmpty());
        card.requiredFieldNotification();
    }

    @Test
    void invalidNumber() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getInvalidCardInfo());
        card.declined();
    }

    @Test
    void notEnoughCharInNumber() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getNotEnoughNumbers());
        card.wrongFormatNotification();
    }

    @Test
    void lettersInNumber() {

    }

    @Test
    void specSymbolNumber() {

    }

    @Test
    void invalidMonth() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getInvalidMonth());
        card.wrongFormatNotification();
    }

    @Test
    void lettersInMonth() {

    }

    @Test
    void specSymbolInMonth() {

    }

    @Test
    void inputOneNumberInMonth() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getOneNumberInMonth());
        card.wrongFormatNotification();

    }

    @Test
    void nameInCyrillic() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getCyrillicNameCardInfo());
        card.wrongFormatNotification();
    }

    @Test
    void nameWithHyphen() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getNameWithHyphen());
        card.approved();
    }

    @Test
    void oneLetterInName() {

    }

    @Test
    void specSymbolInName() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getNameWithSpecSymbol());
        card.wrongFormatNotification();
    }

    @Test
    void numbersInName() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getNameWithNumbers());
        card.wrongFormatNotification();

    }

    @Test
    void lessThanCurrentYear() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getInvalidYear());
        card.expiredNotification();
    }

    @Test
    void oneNumberInYear() {


    }

    @Test
    void specSymbolInYear() {

    }

    @Test
    void lettersInYear() {

    }

    @Test
    void lettersInCVV() {
        CardPaymentPage card = tour.chooseCardPayment();
        card.paySuccess(DataHelper.getInvalidCVV());
        card.wrongFormatNotification();

    }

    @Test
    void oneNumberInCVV() {

    }

    @Test
    void specSymbolInCVV() {

    }


}

