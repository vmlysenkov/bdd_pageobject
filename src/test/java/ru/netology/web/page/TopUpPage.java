package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TopUpPage {
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement button = $("[data-test-id=action-transfer]");

    public TopUpPage() {amount.shouldBe(visible);}

    public DashboardPage topUpAccount(String sum, DataHelper.CardNumber cardNumber) {
        amount.setValue(sum);
        from.setValue(String.valueOf(cardNumber));
        button.click();
        return new DashboardPage();
    }

    public boolean getErrorMessage() {
        boolean result = false;
        if ($(withText("Недостаточно средств. Пополните баланс.")).exists()) {
            result = true;
        }
        return result;
    }


}
