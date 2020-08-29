package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.domain.ProjectConstants;
import home.automation.web.gui.components.Item;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class PaymentPage extends BaseProjectPage {

    @FindBy(xpath = "//tr[contains(@class, 'cart_item')]")
    private List<Item> items;

    @FindBy(xpath = "//span[@id='total_price']")
    private ExtendedWebElement orderTotal;

    @FindBy(id = "total_product")
    private ExtendedWebElement totalProducts;

    @FindBy(id = "total_shipping")
    private ExtendedWebElement totalShipping;

    @FindBy(id = "total_price")
    private ExtendedWebElement total;

    @FindBy(xpath = "//a[@class='bankwire']")
    private ExtendedWebElement payByBankWireBtn;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public Item getOrderItem(int index) {
        return items.get(index);
    }

    public void validateOrderTotals(SoftAssert softAssert, String expectedProductTotal, String deliveryPrice) {
        softAssert.assertEquals(expectedProductTotal, totalProducts.getText(), "Wrong products total on PaymentPage!");
        softAssert.assertEquals(deliveryPrice, totalShipping.getText(), "Wrong delivery price on PaymentPage!");
        String expectedTotal = ProjectConstants.CONCAT_DOLLAR.concat(String.valueOf(Double.parseDouble(expectedProductTotal.replaceAll(ProjectConstants.REPLACE_DOLLAR, "")) +
                Double.parseDouble(deliveryPrice.replaceAll(ProjectConstants.REPLACE_DOLLAR, ""))));
        softAssert.assertEquals(total.getText(), expectedTotal, "Wrong order total on PaymentPage!");
    }

    public OrderSummaryPage clickPayByBankWireBtn() {
        payByBankWireBtn.click();
        return new OrderSummaryPage(driver);
    }
}