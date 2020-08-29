package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AuthorizationPage extends BaseProjectPage {

    @FindBy(id = "email_create")
    private ExtendedWebElement emailInput;

    @FindBy(xpath = "//span[contains(., 'Create an account')]")
    private ExtendedWebElement createAccountBtn;

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public void typeEmail(String email) {
        emailInput.type(email);
    }

    public AccountCreationPage clickCreateAnAccountBtn() {
        createAccountBtn.click();
        return new AccountCreationPage(driver);
    }

}