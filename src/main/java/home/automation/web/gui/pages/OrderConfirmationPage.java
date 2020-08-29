package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends BaseProjectPage {

    @FindBy(xpath = "//span[@class='price']/strong")
    private ExtendedWebElement orderTotal;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getOrderTotal() {
        return orderTotal.getText();
    }
}