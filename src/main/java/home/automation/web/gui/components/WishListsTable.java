package home.automation.web.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class WishListsTable extends BaseProjectUIObject {

    @FindBy(xpath = "//table[@class='table table-bordered']//a[contains(., '%s')]")
    private ExtendedWebElement wishList;

    public WishListsTable(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickWishList(String wishListName) {
        wishList.format(wishListName).click();
    }
}