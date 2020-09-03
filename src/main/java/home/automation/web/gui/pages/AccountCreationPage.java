package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.annotations.DisableCacheLookup;
import home.automation.constant.ConfigConstant;
import home.automation.constant.ProjectConstants;
import home.automation.constant.TimeConstants;
import home.automation.web.domain.UserData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.List;

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

    @FindBy(xpath = "//div[@class='alert alert-danger']/p")
    private ExtendedWebElement errorsAmountMessage;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    @DisableCacheLookup
    private List<ExtendedWebElement> errorsList;

    @FindBy(xpath = "(//label[contains(.,'%s')])[%s]/sup")
    private ExtendedWebElement inputLabel;

    @FindBy(xpath = "//div[@class='alert alert-danger']")
    private ExtendedWebElement errorMessageAlert;

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

    public void selectState(String state) {
        stateSelect.select(state);
    }

    public void typeZipcode(String zipcode) {
        zipcodeInput.type(zipcode);
    }

    public void typePhoneNumber(String phoneNum) {
        mobilePhoneInput.type(phoneNum);
    }

    public void clickRegisterBtn() {
        registerBtn.click();
    }

    public void fillUserDataAndClickRegisterBtn(UserData userData) {
        typeFirstName(userData.getFirstName());
        typeLastName(userData.getLastName());
        typePassword(userData.getPassword());
        typeAddressLine1(userData.getAddress());
        typeCity(userData.getCity());
        selectState(userData.getState());
        typeZipcode(userData.getZipcode());
        typePhoneNumber(userData.getMobilePhone());
        clickRegisterBtn();
    }

    public void validateErrorsAmountMessage(SoftAssert softAssert, int expectedAmount) {
        String expectedMessage = String.format(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, "amount")), expectedAmount);
        if(expectedAmount == 1) {
            softAssert.assertEquals(errorsAmountMessage.getText(), expectedMessage.replaceAll("s", "").replaceAll("are", "is"),
                    "Wrong error messages amount!");
        } else {
            softAssert.assertEquals(errorsAmountMessage.getText(),
                    String.format(R.TESTDATA.get(String.format(ConfigConstant.ERROR_MESSAGE_KEY, "amount")), expectedAmount),
                    "Wrong error messages amount!");
        }
    }

    public boolean isErrorPresent(String error) {
        return errorsList.indexOf(error) != -1;
    }

    public void validateErrorMessages(SoftAssert softAssert, List<String> errorMessages) {
        errorsList.forEach(error -> {
            softAssert.assertTrue(errorMessages.indexOf(error.getText()) != -1, String.format("Error message '%s' is wrong!", error.getText()));
        });
    }

    public void validateRequiredFields(SoftAssert softAssert, List<String> expectedFields) {
        expectedFields.forEach(expectedField -> {
            softAssert.assertTrue(inputLabel.format(expectedField, 1).isElementPresent(TimeConstants.THREE_SEC_TIMEOUT),
                    String.format("'%s' field haven't required field marker!", inputLabel.format(expectedField, 1).getText()));
            if (expectedField.equals("First name") || expectedField.equals("Last name")) {
                softAssert.assertTrue(inputLabel.format(expectedField, 2).isElementPresent(TimeConstants.THREE_SEC_TIMEOUT),
                        String.format("'%s' field haven't required field marker!", inputLabel.format(expectedField, 1).getText()));
            }
        });
    }

    public boolean isErrorMessagesAlertPresent() {
        return errorMessageAlert.isElementPresent(TimeConstants.THREE_SEC_TIMEOUT);
    }
}