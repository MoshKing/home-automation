package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class OrderSummaryPage extends BaseProjectPage {

    @FindBy(id = "amount")
    private ExtendedWebElement total;

    @FindBy(xpath = "//span[.='I confirm my order']")
    private ExtendedWebElement confirmOrderBtn;

    public OrderSummaryPage(WebDriver driver) {
        super(driver);
    }

    public String getOrderTotal() {
        return total.getText();
    }

    public OrderConfirmationPage clickConfirmOrderBtn() {
        confirmOrderBtn.click();
        return new OrderConfirmationPage(driver);

    }
}