package home.automation.web.gui.pages;

import home.automation.web.gui.components.WishListItem;
import home.automation.web.gui.components.WishListsTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyWishListPage extends BaseProjectPage {

    @FindBy(xpath = "//table[@class='table table-bordered']")
    private WishListsTable wishListsTable;

    @FindBy(xpath = "//li[contains(@id,'wlp')]")
    private List<WishListItem> wishListItems;

    public MyWishListPage(WebDriver driver) {
        super(driver);
    }

    public WishListsTable getWishListsTable() {
        return wishListsTable;
    }

    public List<WishListItem> getWishListItems() {
        return wishListItems;
    }
}