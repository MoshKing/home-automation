package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.domain.ProductData;
import home.automation.web.domain.ProductSize;
import home.automation.web.gui.components.AddToCartInfoModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductDetailPage extends BaseProjectPage {

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    private ExtendedWebElement quantityInput;

    @FindBy(xpath = "//select[@id='group_1']")
    private ExtendedWebElement sizeSelect;

    @FindBy(xpath = "//button[@name='Submit']")
    private ExtendedWebElement addToCartBtn;

    @FindBy(id = "layer_cart")
    private AddToCartInfoModal addToCartInfoModal;

    @FindBy(xpath = "//span[@itemprop='sku']")
    private ExtendedWebElement itemSkuText;

    @FindBy(xpath = "//li[@class='selected']/a")
    private ExtendedWebElement itemSelectedColor;

    @FindBy(xpath = "//a[contains(@class, 'color_pick')]")
    private List<ExtendedWebElement> itemColorPickList;

    @FindBy(xpath = "//span[@itemprop='price']")
    private ExtendedWebElement itemPrice;

    @FindBy(xpath = "//h1[@itemprop='name']")
    private ExtendedWebElement itemName;

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

    public ProductData getProductData() {
        ProductData productData = new ProductData();
        productData.setName(itemName.getText());
        productData.setSku(itemSkuText.getText());
        productData.setQty(Integer.parseInt(quantityInput.getAttribute("value")));
        productData.setSize(sizeSelect.getSelectedValue());
        productData.setColor(itemSelectedColor.getAttribute("title"));
        productData.setItemPrice(itemPrice.getText());
        return productData;
    }
}