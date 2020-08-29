package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.domain.ProductData;
import home.automation.web.domain.ProjectConstants;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class Item extends BaseProjectUIObject {

    @FindBy(xpath = "//td[@class='cart_description']//p[@class='product-name']")
    private ExtendedWebElement name;

    @FindBy(xpath = "//small[contains(., 'SKU')]")
    private ExtendedWebElement itemSku;

    @FindBy(xpath = "//td[@class='cart_description']//small[contains(., 'Color')]")
    private ExtendedWebElement itemColorAndSize;

    @FindBy(xpath = "//td[@class='cart_unit']//span[@class='price']")
    private ExtendedWebElement itemPrice;

    @FindBy(xpath = "//input[contains(@class, 'cart_quantity_input')]")
    private ExtendedWebElement itemQtyInput;

    @FindBy(xpath = "//td[@class='cart_total']//span[@class='price']")
    private ExtendedWebElement itemTotal;

    public Item(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void verifyProductData(SoftAssert softAssert, ProductData productData) {
        softAssert.assertEquals(name.getText(), productData.getName(), "Wrong item name!");
        String sku = itemSku.getText().replaceAll("SKU : ", "").trim();
        softAssert.assertEquals(sku, productData.getSku(), "Wrong item sku!");
        String[] colorAndSize = itemColorAndSize.getText().split(ProjectConstants.COMMA);
        String color = colorAndSize[0].replaceAll("Color : ", "").trim();
        String size = colorAndSize[1].replaceAll("Size : ", "").trim();
        softAssert.assertEquals(color, productData.getColor(), "Wrong item color!");
        softAssert.assertEquals(size, productData.getSize(), "Wrong item size!");
        String actualPrice = itemPrice.getText().contains("-") ?
                itemPrice.getText().split(ProjectConstants.WHITESPACE)[0] :
                itemPrice.getText();
        softAssert.assertEquals(actualPrice, productData.getItemPrice(), "Wrong item price!");
        softAssert.assertEquals(itemTotal.getText(), productData.getTotal(), "Wrong item total!");
    }

    public String getItemTotal() {
        return itemTotal.getText();
    }
}