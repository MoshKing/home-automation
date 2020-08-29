package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.domain.ProductSize;
import home.automation.web.gui.components.AddToCartInfoModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BaseProjectPage {

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    private ExtendedWebElement quantityInput;

    @FindBy(xpath = "//select[@id='group_1']")
    private ExtendedWebElement sizeSelect;

    @FindBy(xpath = "//button[@name='Submit']")
    private ExtendedWebElement addToCartBtn;

    @FindBy(id = "layer_cart")
    private AddToCartInfoModal addToCartInfoModal;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public void typeQuantity(String quantity) {
        quantityInput.type(quantity);
    }

    public void selectSize(ProductSize productSize) {
        sizeSelect.select(productSize.getAbbreviation());
    }

    public AddToCartInfoModal clickAddToCartButton() {
        addToCartBtn.click();
        return addToCartInfoModal;
    }
}
