package home.automation.web.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BaseProjectPage {

    @FindBy(xpath = "//span[.='My wishlists']")
    private ExtendedWebElement myWishlistBtn;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("http://automationpractice.com/index.php?controller=my-account");
    }

    public MyWishListPage clickMyWishListBtn() {
        myWishlistBtn.click();
        return new MyWishListPage(driver);
    }
}