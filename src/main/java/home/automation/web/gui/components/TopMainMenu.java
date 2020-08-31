package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.gui.pages.AuthorizationPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TopMainMenu extends BaseProjectUIObject {

    @FindBy(xpath = "//a[@class='login']")
    private ExtendedWebElement signInBtn;

    public TopMainMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public AuthorizationPage clickSignInBtn() {
        signInBtn.click();
        return new AuthorizationPage(driver);
    }
}