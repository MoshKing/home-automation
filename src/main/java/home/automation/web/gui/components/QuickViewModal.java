package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.domain.ProductData;
import home.automation.web.domain.ProductSize;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class QuickViewModal extends BaseProjectUIObject {

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    private ExtendedWebElement quantityInput;

    @FindBy(xpath = "//select[@id='group_1']")
    private ExtendedWebElement sizeSelect;

    @FindBy(xpath = "//a[@class='color_pick selected']")
    private ExtendedWebElement itemSelectedColor;

    @FindBy(xpath = "//button[@name='Submit']")
    private ExtendedWebElement addToBagBtn;

    @FindBy(xpath = "//h1[@itemprop='name']")
    private ExtendedWebElement itemName;

    @FindBy(xpath = "//span[@itemprop='sku']")
    private ExtendedWebElement itemSkuText;

    @FindBy(xpath = "//span[@id='our_price_display']")
    private ExtendedWebElement itemPrice;

    @FindBy(xpath = "//iframe[contains(@id,'fancybox-frame')]")
    private ExtendedWebElement quickViewFrame;

    public QuickViewModal(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void typeQuantity(String qty) {
        driver.switchTo().frame(quickViewFrame.getElement());
        quantityInput.type(qty);
        driver.switchTo().defaultContent();
    }

    public void selectSize(ProductSize productSize) {
        driver.switchTo().frame(quickViewFrame.getElement());
        sizeSelect.select(productSize.getAbbreviation());
        driver.switchTo().defaultContent();
    }

    public AddToCartInfoModal clickAddToBagBtn() {
        driver.switchTo().frame(quickViewFrame.getElement());
        addToBagBtn.click();
        driver.switchTo().defaultContent();
        return new AddToCartInfoModal(driver, driver);
    }

    public ProductData getProductData() {
        driver.switchTo().frame(quickViewFrame.getElement());
        ProductData productData = new ProductData();
        productData.setName(itemName.getText());
        productData.setSku(itemSkuText.getText());
        productData.setQty(Integer.parseInt(quantityInput.getAttribute("value")));
        productData.setSize(sizeSelect.getSelectedValue());
        productData.setColor(itemSelectedColor.getAttribute("title"));
        productData.setItemPrice(itemPrice.getText());
        driver.switchTo().defaultContent();
        return productData;
    }

}