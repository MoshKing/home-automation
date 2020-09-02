package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.PageOpeningStrategy;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.annotations.DisableCacheLookup;
import home.automation.web.gui.components.QuickViewModal;
import home.automation.web.gui.components.SearchResultsItem;
import home.automation.web.domain.SortOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BaseProjectPage {

    @FindBy(xpath = "//div[@class='product-container']")
    @DisableCacheLookup
    private List<SearchResultsItem> searchResultsItems;

    @FindBy(xpath = "//h1[contains(@class, 'page-heading')]")
    private ExtendedWebElement searchResultsHeading;

    @FindBy(xpath = "//div[@class='primary_block row']")
    private QuickViewModal quickViewModal;

    @FindBy(xpath = "//select[@id='selectProductSort']")
    private ExtendedWebElement sortOptionSelect;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(searchResultsHeading);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
    }

    public ProductDetailPage openProductDetails(int searchResultsItemIndex) {
        return searchResultsItems.get(searchResultsItemIndex).openDetails();
    }

    public QuickViewModal openQuickViewModal(int searchResultsItemIndex) {
        searchResultsItems.get(searchResultsItemIndex).clickQuickViewBtn();
        return quickViewModal;
    }

    public List<SearchResultsItem> getSearchResults() {
        return searchResultsItems;
    }

    public void selectSortOption(SortOption sortOption) {
        sortOptionSelect.select(sortOption.getOptionName());
    }

    public List<Double> getPrices() {
        List<Double> prices = new ArrayList();
        for (int i = 0; i < searchResultsItems.size(); i++) {
            prices.add(searchResultsItems.get(i).getPrice(i));
        }
        return prices;
    }

    public List<String> getProductsNames() {
        List<String> productsNames = new ArrayList();
        for(int i = 0; i < searchResultsItems.size(); i++ ) {
            productsNames.add(searchResultsItems.get(i).getProductName(i));
        }
        return productsNames;
    }

    public void validateProductInStock(SoftAssert softAssert) {
        softAssert.assertTrue(searchResultsItems.stream()
                .filter(searchResultsItem -> searchResultsItem.isInStock())
                .collect(Collectors.toList()).size() == searchResultsItems.size(),
                "Search results have not 'In stock' products!");
    }
}
