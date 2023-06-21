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

        DataHelper.getValidCardInfo().withMonth(DataHelper.invalidMonth());
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

