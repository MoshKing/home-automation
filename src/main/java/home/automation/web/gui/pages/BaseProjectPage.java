package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.gui.AbstractPage;
import home.automation.web.gui.components.TopMainMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class BaseProjectPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='nav']")
    private TopMainMenu topMainMenu;

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    public TopMainMenu getTopMainMenu() {
        return topMainMenu;
    }
}
