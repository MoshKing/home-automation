package home.automation.web.gui.pages;

import org.openqa.selenium.WebDriver;

public class CartPage extends BaseProjectPage {
    public CartPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("http://automationpractice.com/index.php?controller=order");
    }
}
