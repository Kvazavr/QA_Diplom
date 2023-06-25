package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;

public class MarrakeshDebitCardTest {
    TourDescriptionPage tour;
    CardPaymentPage card;


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
        DBUtils.prepareDb();
        open("http://localhost:8080");
        tour = new TourDescriptionPage();
        card = tour.chooseCardPayment();
    }

    @Test
    void shouldBeSuccessfulBuyTourDebit() {
        CardInfo cardInfo = DataHelper.getValidCardInfo();
        card.pay(cardInfo);
        card.approved();
        PaymentEntity entity = DBUtils.paymentEntity();
        Assertions.assertEquals("APPROVED", entity.getStatus());
        Assertions.assertEquals(DataHelper.amount(), entity.getAmount());
    }

    @Test
    void shouldBeDeclinedBuyTourDebit() {
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.cardNumberDeclined()));
        card.declined();
        PaymentEntity entity = DBUtils.paymentEntity();
        Assertions.assertEquals("DECLINED", entity.getStatus());
    }

    @Test
    void emptyFieldNumber() {

        card.pay(DataHelper.getValidCardInfo().withNumber(""));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void emptyFieldMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth(""));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void emptyFieldYear() {
        card.pay(DataHelper.getValidCardInfo().withYear(""));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void emptyFieldName() {
        card.pay(DataHelper.getValidCardInfo().withName(""));
        card.requiredFieldNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void emptyFieldCVV() {
        card.pay(DataHelper.getValidCardInfo().withCVV(""));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void invalidNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.invalidCardNumber()));
        card.declined();
        PaymentEntity entity = DBUtils.paymentEntity();
        Assertions.assertNotNull(entity);
        Assertions.assertEquals("DECLINED", entity.getStatus());
    }

    @Test
    void notEnoughCharInNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.notEnoughInCardNumber()));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void lettersInNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber("fdhsjk"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void specSymbolNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber("+++---"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void invalidMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("13"));
        card.wrongValidityNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void lettersInMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("df"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void specSymbolInMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("+@"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void inputOneNumberInMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("9"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void nameInCyrillic() {
        card.pay(DataHelper.getValidCardInfo().withName("Елена"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void nameWithHyphen() {
        card.pay(DataHelper.getValidCardInfo().withName("Elena-Anna"));
        card.approved();
        PaymentEntity entity = DBUtils.paymentEntity();
        Assertions.assertEquals("APPROVED", entity.getStatus());
        Assertions.assertEquals(DataHelper.amount(), entity.getAmount());
    }

    @Test
    void oneLetterInName() {
        card.pay(DataHelper.getValidCardInfo().withName("У"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void specSymbolInName() {
        card.pay(DataHelper.getValidCardInfo().withName("+++---"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void numbersInName() {
        card.pay(DataHelper.getValidCardInfo().withName("123456"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void lessThanCurrentYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("22"));
        card.expiredNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void oneNumberInYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("2"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void specSymbolInYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("++"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void lettersInYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("sd"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void lettersInCVV() {
        card.pay(DataHelper.getValidCardInfo().withCVV("sdd"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void oneNumberInCVV() {
        card.pay(DataHelper.getValidCardInfo().withCVV("1"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void specSymbolInCVV() {
        card.pay(DataHelper.getValidCardInfo().withCVV("+@-"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }


}

