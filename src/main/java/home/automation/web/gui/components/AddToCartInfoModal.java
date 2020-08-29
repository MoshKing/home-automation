package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import home.automation.web.gui.pages.CartPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AddToCartInfoModal extends AbstractUIObject {

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private ExtendedWebElement proceedToCheckoutBtn;

    public AddToCartInfoModal(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CartPage clickProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
        return new CartPage(driver);
    }
}
