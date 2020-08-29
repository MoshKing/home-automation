package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.gui.components.Item;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BaseProjectPage {

    @FindBy(xpath = "//tr[contains(@class, 'cart_item')]")
    private List<Item> items;

    @FindBy(xpath = "//span[.='Proceed to checkout']")
    private ExtendedWebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//tr[contains(@class, 'cart_item')]")
    private ExtendedWebElement item;

    public CartPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("http://automationpractice.com/index.php?controller=order");
        setUiLoadedMarker(item);
    }

    public Item getCartItem(int index) {
        return items.get(index);
    }

    public void clickProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
    }
}
