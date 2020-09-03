package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.domain.ProductData;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class WishListItem extends BaseProjectUIObject {

    @FindBy(xpath = ".//p[@id='s_title']")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//input[contains(@id, 'quantity')]")
    private ExtendedWebElement productQty;

    public WishListItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ProductData getItemData() {
        ProductData productData = new ProductData();
        productData.setName(productName.getText().trim());
        productData.setQty(Integer.parseInt(productQty.getAttribute("value")));
        return  productData;
    }
}