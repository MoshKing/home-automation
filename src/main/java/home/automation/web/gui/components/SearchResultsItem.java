package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.constant.ProjectConstants;
import home.automation.constant.TimeConstants;
import home.automation.web.gui.pages.ProductDetailPage;
import home.automation.web.util.JSUtil;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SearchResultsItem extends BaseProjectUIObject {

    @FindBy(xpath = "//span[text()='More']")
    private ExtendedWebElement moreBtn;

    @FindBy(xpath = ".//div[@class='product-image-container']")
    private ExtendedWebElement itemImage;

    @FindBy(xpath = "//a[@class='quick-view']")
    private ExtendedWebElement quickViewBtn;

    @FindBy(xpath = "(//div[@class='product-container']//span[@itemprop='price'])[%s]")
    private ExtendedWebElement priceByIndex;

    @FindBy(xpath = "(//div[@class='product-container']//a[@class='product-name'])[%s]")
    private ExtendedWebElement productNameByIndex;

    @FindBy(xpath = ".//span[@itemprop='offers']")
    private ExtendedWebElement inStockLabel;

    @FindBy(xpath = ".//a[contains(@class,'addToWishlist wishlistProd')]")
    private ExtendedWebElement addToWishListBtn;

    public SearchResultsItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ProductDetailPage openDetails() {
        itemImage.hover();
        moreBtn.click();
        return new ProductDetailPage(driver);
    }

    public void clickQuickViewBtn() {
        hoverItem();
        JSUtil.clickElement(driver, quickViewBtn);
    }

    public Double getPrice(int index) {
        return Double.parseDouble(priceByIndex
                .format((index + 1) * 2).getText().replaceAll(ProjectConstants.WHITESPACE, "").replaceAll(ProjectConstants.REPLACE_DOLLAR, ""));
    }

    public String getProductName(int index) {
        return productNameByIndex.format(index + 1).getText().trim();
    }

    public boolean isInStock() {
        return inStockLabel.isElementPresent(TimeConstants.THREE_SEC_TIMEOUT);
    }

    public void clickAddToWishList() {
        hoverItem();
        addToWishListBtn.click();
    }

    private void hoverItem() {
        new Actions(driver)
                .moveToElement(itemImage.getElement())
                .build()
                .perform();
        pause(1);
    }
}