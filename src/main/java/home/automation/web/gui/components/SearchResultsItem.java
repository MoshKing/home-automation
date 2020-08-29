package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import home.automation.web.gui.pages.ProductDetailPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsItem extends AbstractUIObject {

    @FindBy(xpath = "//span[text()='More']")
    private ExtendedWebElement moreBtn;

    @FindBy(xpath = "//div[@class='product-image-container']")
    private ExtendedWebElement itemImage;

    public SearchResultsItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ProductDetailPage openDetails() {
        itemImage.hover();
        moreBtn.click();
        return new ProductDetailPage(driver);
    }

}
