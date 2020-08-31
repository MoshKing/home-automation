package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import cucumber.api.java.cs.A;
import home.automation.web.gui.pages.ProductDetailPage;
import home.automation.web.util.JSUtil;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SearchResultsItem extends BaseProjectUIObject {

    @FindBy(xpath = "//span[text()='More']")
    private ExtendedWebElement moreBtn;

    @FindBy(xpath = "//div[@class='product-image-container']")
    private ExtendedWebElement itemImage;

    @FindBy(xpath = "//a[@class='quick-view']")
    private ExtendedWebElement quickViewBtn;

    public SearchResultsItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ProductDetailPage openDetails() {
        itemImage.hover();
        moreBtn.click();
        return new ProductDetailPage(driver);
    }

    public void clickQuickViewBtn() {
        new Actions(driver)
                .moveToElement(itemImage.getElement())
                .build()
                .perform();
        pause(1);
        JSUtil.clickElement(driver, quickViewBtn);
    }

}