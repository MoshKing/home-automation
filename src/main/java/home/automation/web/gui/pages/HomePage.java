package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseProjectPage {

    @FindBy(id = "search_query_top")
    private ExtendedWebElement searchInput;

    @FindBy(name = "submit_search")
    private ExtendedWebElement submitSearchBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(searchInput);
        setPageAbsoluteURL("http://automationpractice.com/index.php");
    }

    public void typeSearchQuery(String searchQuery) {
        searchInput.type(searchQuery);
    }

    public SearchResultsPage clickSubmitSearchBtn() {
        submitSearchBtn.click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage searchProducts(String searchQuery) {
        typeSearchQuery(searchQuery);
        return clickSubmitSearchBtn();
    }
}
