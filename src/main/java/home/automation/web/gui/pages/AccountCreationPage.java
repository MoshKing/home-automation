package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.web.domain.UserData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AccountCreationPage extends BaseProjectPage {

    @FindBy(id = "customer_firstname")
    private ExtendedWebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    private ExtendedWebElement lastNameInput;

    @FindBy(xpath = "//input[@type='password']")
    private ExtendedWebElement passwdInput;

    @FindBy(id = "address1")
    private ExtendedWebElement addressLine1Input;

    @FindBy(id = "city")
    private ExtendedWebElement cityInput;

    @FindBy(id = "id_state")
    private ExtendedWebElement stateSelect;

    @FindBy(id = "postcode")
    private ExtendedWebElement zipcodeInput;

    @FindBy(id = "phone_mobile")
    private ExtendedWebElement mobilePhoneInput;

    @FindBy(xpath = "//span[.='Register']")
    private ExtendedWebElement registerBtn;

    public AccountCreationPage(WebDriver driver) {
        super(driver);
    }

    public void typeFirstName(String firstName) {
        firstNameInput.type(firstName);
    }

    public void typeLastName(String lastName) {
        lastNameInput.type(lastName);
    }

    public void typePassword(String passwd) {
        passwdInput.type(passwd);
    }

    public void typeAddressLine1(String addressLine1) {
        addressLine1Input.type(addressLine1);
    }

    public void typeCity(String city) {
        cityInput.type(city);
    }

    public void typeState(String state) {
        stateSelect.select(state);
    }

    public void typeZipcode(String zipcode) {
        zipcodeInput.type(zipcode);
    }

    public void typePhoneNumber(String phoneNum) {
        mobilePhoneInput.type(phoneNum);
    }

    public AddressesPage clickRegisterBtn() {
        registerBtn.click();
        return new AddressesPage(driver);
    }

    public AddressesPage fillUserDataAndClickRegisterBtn(UserData userData) {
        typeFirstName(userData.getFirstName());
        typeLastName(userData.getLastName());
        typePassword(userData.getPassword());
        typeAddressLine1(userData.getAddress());
        typeCity(userData.getCity());
        typeState(userData.getState());
        typeZipcode(userData.getZipcode());
        typePhoneNumber(userData.getMobilePhone());
        return clickRegisterBtn();
    }

}