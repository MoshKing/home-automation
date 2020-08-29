package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.domain.UserData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AuthorizationPage extends BaseProjectPage {

    @FindBy(id = "email_create")
    private ExtendedWebElement createEmailInput;

    @FindBy(xpath = "//span[contains(., 'Create an account')]")
    private ExtendedWebElement createAccountBtn;

    @FindBy(xpath = "//input[@id='email']")
    private ExtendedWebElement registeredEmailInput;

    @FindBy(xpath = "//input[@id='passwd']")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    private ExtendedWebElement signInBtn;

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public void typeNewUserEmail(String email) {
        createEmailInput.type(email);
    }

    public AccountCreationPage clickCreateAnAccountBtn() {
        createAccountBtn.click();
        return new AccountCreationPage(driver);
    }

    public void typeRegisteredEmail(String email) {
        registeredEmailInput.type(email);
    }

    public void typePassword(String password) {
        passwordInput.type(password);
    }

    public AddressesPage clickSignInBtn() {
        signInBtn.click();
        return new AddressesPage(driver);
    }

    public AddressesPage signIn(UserData registeredUser) {
        typeRegisteredEmail(registeredUser.getEmail());
        typePassword(registeredUser.getPassword());
        return clickSignInBtn();
    }

}