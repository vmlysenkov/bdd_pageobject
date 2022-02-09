package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private final SelenideElement firstCard = $("[data-test-id ='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private final SelenideElement firstTopUpButton = $$("[data-test-id=action-deposit]").first();

    private final SelenideElement secondCard = $("[data-test-id ='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private final SelenideElement secondTopUpButton = $$("[data-test-id=action-deposit]").last();

    public TopUpPage firstTopUpButton() {
        firstTopUpButton.click();
        return new TopUpPage();
    }

    public TopUpPage secondTopUpButton() {
        secondTopUpButton.click();
        return new TopUpPage();
    }

    public int getFirstCardBalance() {
        val text = firstCard.text();
        return extractFirstCardBalance(text);
    }

    private int extractFirstCardBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getSecondCardBalance() {
        val text = secondCard.text();
        return extractSecondCardBalance(text);
    }

    private int extractSecondCardBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
