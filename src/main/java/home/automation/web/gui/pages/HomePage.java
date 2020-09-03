package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseProjectPage {

    public HomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(searchInput);
        setPageAbsoluteURL("http://automationpractice.com/index.php");
    }


}
