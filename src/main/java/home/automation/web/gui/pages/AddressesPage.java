package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import home.automation.constant.ProjectConstants;
import home.automation.web.domain.UserData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class AddressesPage extends BaseProjectPage {

    @FindBy(xpath = "//ul[@id='address_delivery']//li[contains(@class, 'address_firstname')]")
    private ExtendedWebElement firstAndLastName;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[contains(@class, 'address_address1')]")
    private ExtendedWebElement addressLine1;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[contains(@class, 'address_city')]")
    private ExtendedWebElement cityStateAndZipcode;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[contains(@class, 'address_country_name')]")
    private ExtendedWebElement country;

    @FindBy(xpath = "//ul[@id='address_delivery']//li[contains(@class, 'address_phone_mobile')]")
    private ExtendedWebElement phoneNumber;

    @FindBy(xpath = "//button[@type='submit' and contains(., 'Proceed to checkout')]")
    private ExtendedWebElement proceedToCheckoutBtn;

    public AddressesPage(WebDriver driver) {
        super(driver);
    }

    public void verifyDeliveryAddress(SoftAssert softAssert, UserData userData) {
        String expectedLine = userData.getFirstName().concat(ProjectConstants.WHITESPACE).concat(userData.getLastName());
        softAssert.assertEquals(firstAndLastName.getText(), expectedLine, "Wrong first and last name on AddressesPage!");
        softAssert.assertEquals(addressLine1.getText(), userData.getAddress(), "Wrong address line 1 on AddressesPage!");
        expectedLine = userData.getCity().concat(ProjectConstants.COMMA).concat(ProjectConstants.WHITESPACE)
                .concat(userData.getState()).concat(ProjectConstants.WHITESPACE).concat(userData.getZipcode());
        softAssert.assertEquals(cityStateAndZipcode.getText(), expectedLine, "Wrong city, state and zipcode line on AddressesPage!");
        softAssert.assertEquals(country.getText(), userData.getCountry(), "Wrong country line on AddressesPage!");
        softAssert.assertEquals(phoneNumber.getText(), userData.getMobilePhone(), "Wrong mobil phone line on AddressesPage!");
    }

    public ShippingPage clickProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
        return new ShippingPage(driver);
    }
}
