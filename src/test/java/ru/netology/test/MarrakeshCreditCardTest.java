package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.CreditRequestEntity;
import ru.netology.data.DBUtils;
import ru.netology.data.DataHelper;
import ru.netology.page.CreditPaymentPage;
import ru.netology.page.TourDescriptionPage;

import static com.codeborne.selenide.Selenide.open;

public class MarrakeshCreditCardTest {
    TourDescriptionPage tour;
    CreditPaymentPage card;

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
        card = tour.chooseCreditPayment();
    }

    @Test
    void shouldBeSuccessfulPurchaseTourCredit() {

        CardInfo cardInfo = DataHelper.getValidCardInfo();
        card.pay(cardInfo);
        card.approved();
        CreditRequestEntity entity = DBUtils.creditRequestEntity();
        Assertions.assertEquals("APPROVED", entity.getStatus());
    }

    @Test
    void shouldBeDeclinedPurchaseWhenUseDeclinedCard() {
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.cardNumberDeclined()));
        card.declined();
        CreditRequestEntity entity = DBUtils.creditRequestEntity();
        Assertions.assertEquals("DECLINED", entity.getStatus());
    }

    @Test
    void cannotMakePurchaseWithEmptyFieldNumber() {

        card.pay(DataHelper.getValidCardInfo().withNumber(""));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithEmptyFieldMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth(""));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithEmptyFieldYear() {
        card.pay(DataHelper.getValidCardInfo().withYear(""));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void cannotMakePurchaseWithEmptyFieldName() {
        card.pay(DataHelper.getValidCardInfo().withName(""));
        card.requiredFieldNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithEmptyFieldCVV() {
        card.pay(DataHelper.getValidCardInfo().withCVV(""));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithInvalidNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.invalidCardNumber()));
        card.declined();
        CreditRequestEntity entity = DBUtils.creditRequestEntity();
        Assertions.assertNotNull(entity);
        Assertions.assertEquals("DECLINED", entity.getStatus());
    }

    @Test
    void cannotMakePurchaseWithNotEnoughCharInNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber(DataHelper.notEnoughInCardNumber()));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithLettersInNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber("fdhsjk"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void cannotMakePurchaseWithSpecSymbolNumber() {
        card.pay(DataHelper.getValidCardInfo().withNumber("+++---"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithInvalidMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("13"));
        card.wrongValidityNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithLettersInMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("df"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithSpecSymbolInMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("+@"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void cannotMakePurchaseWithInputOneNumberInMonth() {
        card.pay(DataHelper.getValidCardInfo().withMonth("9"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithNameInCyrillic() {
        card.pay(DataHelper.getValidCardInfo().withName("Елена"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void shouldBeSuccessfulPurchaseWithNameWithHyphen() {
        card.pay(DataHelper.getValidCardInfo().withName("Elena-Anna"));
        card.approved();
        CreditRequestEntity entity = DBUtils.creditRequestEntity();
        Assertions.assertEquals("APPROVED", entity.getStatus());
    }

    @Test
    void cannotMakePurchaseWithOneLetterInName() {
        card.pay(DataHelper.getValidCardInfo().withName("У"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithSpecSymbolInName() {
        card.pay(DataHelper.getValidCardInfo().withName("+++---"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithNumbersInName() {
        card.pay(DataHelper.getValidCardInfo().withName("123456"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }
    @Test
    void cannotMakePurchaseWithRowOfLettersInName() {
        card.pay(DataHelper.getValidCardInfo().withName("vdnklcdd"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWhenLessThanCurrentYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("22"));
        card.expiredNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithOneNumberInYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("2"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();
    }

    @Test
    void cannotMakePurchaseWithSpecSymbolInYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("++"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void cannotMakePurchaseWithLettersInYear() {
        card.pay(DataHelper.getValidCardInfo().withYear("sd"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void cannotMakePurchaseWithLettersInCVV() {
        card.pay(DataHelper.getValidCardInfo().withCVV("sdd"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }
    @Test
    void cannotMakePurchaseWithOneNumberInCVV() {
        card.pay(DataHelper.getValidCardInfo().withCVV("1"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }

    @Test
    void cannotMakePurchaseWithSpecSymbolInCVV() {
        card.pay(DataHelper.getValidCardInfo().withCVV("+@-"));
        card.wrongFormatNotification();
        DBUtils.assertDbEmpty();

    }
}
