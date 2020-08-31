package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import home.automation.web.domain.ProductData;
import home.automation.web.domain.ProjectConstants;
import home.automation.web.gui.pages.CartPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class AddToCartInfoModal extends BaseProjectUIObject {

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private ExtendedWebElement proceedToCheckoutBtn;

    @FindBy(id = "layer_cart_product_attributes")
    private ExtendedWebElement colorAndSizeText;

    @FindBy(id = "layer_cart_product_quantity")
    private ExtendedWebElement itemQty;

    @FindBy(id = "layer_cart_product_price")
    private ExtendedWebElement itemTotal;

    @FindBy(id = "layer_cart_product_title")
    private ExtendedWebElement itemName;

    public AddToCartInfoModal(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
        setUiLoadedMarker(colorAndSizeText);
    }

    public CartPage clickProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
        return new CartPage(driver);
    }

    public void verifyProductData(SoftAssert softAssert, ProductData productData) {
        String[] colorAndSize = colorAndSizeText.getText().split(ProjectConstants.COMMA);
        String color = colorAndSize[0];
        String size = colorAndSize[1].replaceAll(ProjectConstants.WHITESPACE, "");
        softAssert.assertEquals(color, productData.getColor(), "Wrong product color in AddToCartInfoModal!");
        softAssert.assertEquals(size, productData.getSize(), "Wrong product size in AddToCartInfoModal!");
        softAssert.assertEquals(itemName.getText(), productData.getName(), "Wrong product name in AddToCartInfoModal!");
        softAssert.assertEquals(itemTotal.getText(), productData.getTotal(), "Wrong product total in AddToCartInfoModal!");
    }
}
