package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import data.SQLHelper;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static data.SQLHelper.cleanDatabase;

public class BuyingTourTestInCredit {
    MainPage mainPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("Allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        mainPage = open("http://localhost:8080/", MainPage.class);
    }

    @AfterEach
    void tearDownAllDatabase() {
        cleanDatabase();
    }

    @Test
    @DisplayName("Successful purchase of a tour with payment on credit with valid APPROVED card.")
    void shouldSuccessfulPurchaseOfTourInCreditWithValidApprovedDebitCard() {
        mainPage.chooseByInCredit("Кредит по данным карты");
        mainPage.enteringApprovedCard();
        mainPage.enteringValidCardValidityPeriod();
        mainPage.enteringValidOwner();
        mainPage.enteringValidCVC();
        mainPage.verifySuccessfulNotification("Операция одобрена Банком.");
        var actualStatusLastLineCreditRequestEntity = SQLHelper.getStatusLastLineCreditRequestEntity();
        var expectedStatus = "APPROVED";
        assertEquals(actualStatusLastLineCreditRequestEntity, expectedStatus);
    }

    @Test
    @DisplayName("The bank's refusal to purchase a tour on credit when filling out the form with valid DECLINED card data.")
    void shouldUnsuccessfulPurchaseOfTourInCreditWithValidDeclancedDebitCard() {
        mainPage.chooseByInCredit("Кредит по данным карты");
        mainPage.enteringDeclinedCard();
        mainPage.enteringValidCardValidityPeriod();
        mainPage.enteringValidOwner();
        mainPage.enteringValidCVC();
        mainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
        var actualStatusLastLineCreditRequestEntity = SQLHelper.getStatusLastLinePaymentRequestEntity();
        var expectedStatus = "DECLINED";
        assertEquals(actualStatusLastLineCreditRequestEntity, expectedStatus);
    }

    @Test
    @DisplayName("The bank's refusal to purchase a tour on credit when filling out the form with card data that is not registered in the system.")
    void shouldUnsuccessfulPurchaseOfTourInCreditWithDebitCardNotRegisteredInSystem() {
        mainPage.chooseByInCredit("Кредит по данным карты");
        mainPage.enteringRandomCard();
        mainPage.enteringValidCardValidityPeriod();
        mainPage.enteringValidOwner();
        mainPage.enteringValidCVC();
        mainPage.verifyErrorNotification("Ошибка! Банк отказал в проведении операции.");
    }
}