package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.gui.pages.AuthorizationPage;
import home.automation.web.gui.pages.MyAccountPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TopMainMenu extends BaseProjectUIObject {

    @FindBy(xpath = ".//a[@class='login']")
    private ExtendedWebElement signInBtn;

    @FindBy(xpath = ".//a[@class='account']")
    private ExtendedWebElement userAccountBtn;

    public TopMainMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public AuthorizationPage clickSignInBtn() {
        signInBtn.click();
        return new AuthorizationPage(driver);
    }

    public MyAccountPage clickUserAccountBtn() {
        userAccountBtn.click();
        return new MyAccountPage(driver);
    }
}