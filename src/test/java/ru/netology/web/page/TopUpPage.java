package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TopUpPage {
    private SelenideElement amount = $("[data-test-id=amount]");
    private SelenideElement from = $("[data-test-id=from]");
    private SelenideElement button = $("[data-test-id=action-transfer]");

    public DashboardPage topUpAccount(String sum, DataHelper.CardNumber cardNumber) {
        amount.setValue(sum);
        from.setValue(String.valueOf(cardNumber));
        button.click();
        return new DashboardPage();
    }


}
