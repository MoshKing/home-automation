package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import home.automation.web.gui.components.TopMainMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class BaseProjectPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='nav']")
    protected TopMainMenu topMainMenu;

    @FindBy(id = "search_query_top")
    protected ExtendedWebElement searchInput;

    @FindBy(name = "submit_search")
    protected ExtendedWebElement submitSearchBtn;

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    public TopMainMenu getTopMainMenu() {
        return topMainMenu;
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
