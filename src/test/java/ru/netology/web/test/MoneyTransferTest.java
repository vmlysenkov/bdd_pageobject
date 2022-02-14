package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TopUpPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTransferTest {

    @BeforeEach
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://0.0.0.0:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationInfo = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationInfo);
    }

    @Test
    void shouldTopUpFirstCard() {
        var dashboardPage = new DashboardPage();
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        var topUpPage = dashboardPage.firstTopUpButton();
        var cardNumber = DataHelper.getSecondCardNumber();
        String sum = "1000";
        topUpPage.topUpAccount(sum, cardNumber);
        assertEquals(firstCardBalance + Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalance - Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTopUpSecondCard() {
        var dashboardPage = new DashboardPage();
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        var topUpPage = dashboardPage.secondTopUpButton();
        var cardNumber = DataHelper.getFirstCardNumber();
        String sum = "3000";
        topUpPage.topUpAccount(sum, cardNumber);
        assertEquals(firstCardBalance - Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalance + Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldNotTopUpFirstCard() {
        var dashboardPage = new DashboardPage();
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        var topUpPage = dashboardPage.firstTopUpButton();
        var cardNumber = DataHelper.getSecondCardNumber();
        String sum = "20000";
        topUpPage.topUpAccount(sum, cardNumber);
        var topUp = new TopUpPage();
        var errorMessage = topUp.getErrorMessage();
        assertTrue(errorMessage, String.valueOf(true));
        assertEquals(firstCardBalance, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalance, dashboardPage.getSecondCardBalance());
    }
}
