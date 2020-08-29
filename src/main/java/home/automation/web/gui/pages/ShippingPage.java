package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ShippingPage extends BaseProjectPage {

    @FindBy(xpath = "//input[@id='cgv']/parent::span")
    private ExtendedWebElement termsAndConditionsCheckbox;

    @FindBy(xpath = "//button[@type='submit' and contains(., 'Proceed to checkout')]")
    private ExtendedWebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//td[@class='delivery_option_price']")
    private ExtendedWebElement deliveryPrice;

    public ShippingPage(WebDriver driver) {
        super(driver);
    }

    public void changeTermsAndConditionsCheckboxState(boolean isChecked) {
        boolean isCheckedNow = termsAndConditionsCheckbox.getAttribute("class").equals("checked");
        if(isCheckedNow != isChecked) {
            termsAndConditionsCheckbox.click();
        }
    }

    public PaymentPage clickProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
        return new PaymentPage(driver);
    }

    public String getDeliveryPrice() {
        return deliveryPrice.getText();
    }
}