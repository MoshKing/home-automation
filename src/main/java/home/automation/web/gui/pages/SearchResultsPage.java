package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.gui.components.QuickViewModal;
import home.automation.web.gui.components.SearchResultsItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BaseProjectPage {

    @FindBy(xpath = "//div[@class='product-container']")
    private List<SearchResultsItem> searchResultsItem;

    @FindBy(xpath = "//h1[contains(@class, 'page-heading')]")
    private ExtendedWebElement searchResultsHeading;

    @FindBy(xpath = "//div[@class='primary_block row']")
    private QuickViewModal quickViewModal;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(searchResultsHeading);
    }

    public ProductDetailPage openProductDetails(int searchResultsItemIndex) {
        return searchResultsItem.get(searchResultsItemIndex).openDetails();
    }

    public QuickViewModal openQuickViewModal(int searchResultsItemIndex) {
        searchResultsItem.get(searchResultsItemIndex).clickQuickViewBtn();
        return quickViewModal;
    }
}
